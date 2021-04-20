package com.collectplatform.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.collectplatform.project.dao.ProjectDao;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.exception.ScriptFileException;
import com.collectplatform.project.property.ScriptFileProperties;
import com.collectplatform.project.service.ScriptFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/15
 */

@Service
public class ScriptScriptFileServiceImpl implements ScriptFileService {

    @Autowired
    ScriptFileProperties scriptFileProperties;

    @Autowired
    private ProjectDao projectDao;

    @Override
    public String storeFile(MultipartFile file, String id) {
        mkdir(scriptFileProperties.getUploadDir() + File.separator + id);
        return savaFile(file, id);
    }

    @Override
    public Resource loadFileAsResource(String fileName, String id) {
        try {
            Path filePath = Paths.get(scriptFileProperties.getUploadDir(), id, fileName);
            System.out.println(filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ScriptFileException(fileName + "文件不存在");
            }
        } catch (MalformedURLException e) {
            throw new ScriptFileException(fileName + "文件不存在", e);
        }
    }

    @Override
    public String deleteFile(String fileName, String id) {
        return delFile(fileName, id);
    }

    @Override
    public String changeFile(MultipartFile file, String id) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        delFile(fileName, id);
        return savaFile(file, id);
    }


    private void mkdir(String path) {
        System.out.println(path);
        File fd = null;
        File file = new File(path);
        try {
            fd = new File(path);
            if (!fd.exists()) {
                fd.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fd = null;
        }
    }


    public String savaFile(MultipartFile file, String id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new ScriptFileException("请检查文件名" + fileName);
            }
            Path targetLocation = Paths.get(scriptFileProperties.getUploadDir(), id, fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            UpdateWrapper<ProjectEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setFileName(fileName);
            projectDao.update(projectEntity, updateWrapper);
            return fileName;
        } catch (IOException e) {
            throw new ScriptFileException(fileName + "文件存储失败请重试", e);
        }
    }
    public String delFile(String fileName, String id) {
        String filePath = scriptFileProperties.getUploadDir() + "/" + id + "/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new ScriptFileException("文件不存在");
        } else {
            file.delete();
            return "删除成功";
        }
    }

}
