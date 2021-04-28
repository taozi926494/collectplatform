package com.collectplatform.project.controller;

import com.collectplatform.core.common.R;
import com.collectplatform.project.dto.UploadFileResponse;
//import com.collectplatform.project.feign.ExcutorScriptFileClient;
import com.collectplatform.project.service.ScriptFileService;
import com.collectplatform.project.vo.ScriptFileVo.CreatFileVo;
import com.collectplatform.project.vo.ScriptFileVo.CreateFolderVo;
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
import javax.validation.Valid;
import java.io.IOException;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */
@RestController
@RequestMapping("file")
public class ScriptFileController {

    private static final Logger logger = LoggerFactory.getLogger(ScriptFileController.class);

    @Autowired
    private ScriptFileService scriptFileService;

    @PostMapping("/upload")
    public R<Object> uploadFile(@RequestParam("file") MultipartFile file, String id) {
        String fileName = scriptFileService.storeFile(file, id);

        return new R<Object>(new UploadFileResponse(fileName,
                file.getContentType(), file.getSize()));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(String id, HttpServletRequest request) {
        Resource resource = scriptFileService.loadFileAsResource(id);

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
    public R<String> delete(String fileName, String id) {
        return new R<String>(scriptFileService.deleteFile(fileName, id));
    }

    @PostMapping("/update")
    public R<Object> updateFile(@RequestParam("file") MultipartFile file, String id) {
        String fileName = scriptFileService.changeFile(file, id);

        return new R<Object>(new UploadFileResponse(fileName,
                file.getContentType(), file.getSize()));
    }

    @GetMapping("/tree")
    public R<Object> fileTree(String id) {
        return new R<Object>(scriptFileService.fileTree(id));
    }

    @PostMapping("create-file")
    public R<String> createFile(@Valid @RequestBody() CreatFileVo creatFileVo) throws IOException {
        return new R<String>(scriptFileService.creatFile(creatFileVo));
    }

    @PostMapping("create-folder")
    public R<String> createFolder(@Valid @RequestBody() CreateFolderVo createFolderVo) {
        return new R<String>(scriptFileService.createFolder(createFolderVo));
    }

//    @GetMapping("/get")
//    public R<String> get() {
//        return new R<>("This is get");
//    }
//
//    @GetMapping("/feign")
//    public R<String> feign() {
//        R<String> res = excutorScriptFileClient.scriptUpload();
//        String data = res.getData();
//        return new R<>("Get Response by feign, Response is ' " + data + " '");
//    }
}
