package com.collectplatform.project.service.impl;

import com.collectplatform.project.exception.FileException;
import com.collectplatform.project.property.FileProperties;
import com.collectplatform.project.service.FileService;
import com.collectplatform.project.vo.LabelVo.DeleteVo;
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
public class FileServiceImpl implements FileService {

    @Autowired
    FileProperties fileProperties;

    @Override
    public String storeFile(MultipartFile file, String id) {
        mkdir(fileProperties.getUploadDir() + File.separator + id);
        return savaFile(file, id);
    }

    @Override
    public Resource loadFileAsResource(String fileName, String id) {
        try {
            Path filePath = Paths.get(fileProperties.getUploadDir(), id, fileName);
            System.out.println(filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException(fileName + "文件不存在");
            }
        } catch (MalformedURLException e) {
            throw new FileException(fileName + "文件不存在", e);
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
                throw new FileException("请检查文件名" + fileName);
            }
            Path targetLocation = Paths.get(fileProperties.getUploadDir(), id, fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileException(fileName + "文件存储失败请重试", e);
        }
    }
    public String delFile(String fileName, String id) {
        String filePath = fileProperties.getUploadDir() + "/" + id + "/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileException("文件不存在");
        } else {
            file.delete();
            return "删除成功";
        }
    }

}
