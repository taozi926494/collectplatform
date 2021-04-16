package com.collectplatform.project.entity;

import lombok.Data;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@Data
public class ProjectEntity {
    private int project_id;
    private String project_name;
    private String media_id;
    private String tag_id;
    private String file_name;
}
