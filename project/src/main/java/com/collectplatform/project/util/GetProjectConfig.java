package com.collectplatform.project.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetProjectConfig {
    @Value("${project.storage-database}")
    private String storageDatabase;

    public String getStorageDatabase() {
        return storageDatabase;
    }
}
