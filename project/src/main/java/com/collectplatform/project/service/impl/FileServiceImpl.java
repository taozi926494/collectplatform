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

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/15
 */

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileProperties fileProperties;

    @Override
    public String storeFile(MultipartFile file, String projectName) {
        mkdir(fileProperties.getUploadDir() + "/" + projectName);
        return savaFile(file, projectName);
    }

    @Override
    public Resource loadFileAsResource(String fileName, String projectName) {
        try {
            Path filePath = Paths.get(fileProperties.getUploadDir(), projectName, fileName);
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
    public String deleteFile(String fileName, String projectName) {
        return delFile(fileName, projectName);
    }

    @Override
    public String changeFile(MultipartFile file, String projectName) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        delFile(fileName, projectName);
        return savaFile(file, projectName);
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


    public String savaFile(MultipartFile file, String projectName) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileException("请检查文件名" + fileName);
            }
            Path targetLocation = Paths.get(fileProperties.getUploadDir(), projectName, fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileException(fileName + "文件存储失败请重试", e);
        }
    }
    public String delFile(String fileName, String projectName) {
        String filePath = fileProperties.getUploadDir() + "/" + projectName + "/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileException("文件不存在");
        } else {
            file.delete();
            return "删除成功";
        }
    }

}
