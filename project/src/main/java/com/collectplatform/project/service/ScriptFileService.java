package com.collectplatform.project.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */

public interface ScriptFileService {
    String storeFile(MultipartFile file, String id);

    Resource loadFileAsResource(String id);

    String deleteFile(String fileName, String id);

    String changeFile(MultipartFile file, String id);

    String deleteTempFile(String id);
}
