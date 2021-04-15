package com.collectplatform.project.service;

import com.collectplatform.project.exception.FileException;
import com.collectplatform.project.property.FileProperties;
import com.collectplatform.project.vo.LabelVo.DeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */

public interface FileService {
    public String storeFile(MultipartFile file, String projectName);

    Resource loadFileAsResource(String fileName, String projectName);

    String deleteFile(String fileName, String projectName);

    String changeFile(MultipartFile file, String projectName);
}
