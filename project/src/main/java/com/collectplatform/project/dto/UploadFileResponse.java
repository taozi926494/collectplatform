package com.collectplatform.project.dto;

import lombok.Data;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */
@Data
public class UploadFileResponse {
    public String fileName;
    public String projectName;
    public String fileType;
    public long size;

    public UploadFileResponse(String fileName, String projectName, String fileType, long size) {
        this.fileName = fileName;
        this.projectName = projectName;
        this.fileType = fileType;
        this.size = size;
    }
}

