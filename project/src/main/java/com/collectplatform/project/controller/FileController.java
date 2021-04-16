package com.collectplatform.project.controller;

import com.collectplatform.core.common.R;
import com.collectplatform.project.dto.UploadFileResponse;
import com.collectplatform.project.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */
@RestController
@RequestMapping("file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public R<Object> uploadFile(@RequestParam("file") MultipartFile file, String projectName) {
        String fileName = fileService.storeFile(file, projectName);

        return new R<Object>(new UploadFileResponse(fileName, projectName,
                file.getContentType(), file.getSize()));
    }


    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(String fileName, String projectName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName, projectName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/delete")
    public R<String> delete(String fileName, String projectName){
        return new R<String>(fileService.deleteFile(fileName, projectName));
    }

    @PostMapping("/update")
    public R<Object> updateFile(@RequestParam("file") MultipartFile file, String projectName) {
        String fileName = fileService.changeFile(file, projectName);

        return new R<Object>(new UploadFileResponse(fileName, projectName,
                file.getContentType(), file.getSize()));
    }
}
