package com.collectplatform.core.common.MPGenerator;

public class ClcConfig {
    public String getAuthor() {
        return "Clc";
    }

    public String getDbUrl() {
        return "jdbc:mysql://localhost:3306/collectplatform?" +
                "characterEncoding=UTF8&&autoReconnect=true&&serverTimezone=Asia/Shanghai";
    }

    public String getDbName() {
        return "root";
    }

    public String getDbPassword() {
        return "Clc372493";
    }
}
