package com.collectplatform.project.dto;

import lombok.Data;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */
@Data
public class UploadFileResponse {
    public String fileName;
    public String fileType;
    public long size;

    public UploadFileResponse(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }
}

