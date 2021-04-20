package com.collectplatform.project.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */

@ConfigurationProperties(prefix = "file")
public class ScriptFileProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    };

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
