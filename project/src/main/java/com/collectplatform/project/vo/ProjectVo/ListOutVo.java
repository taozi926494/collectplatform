package com.collectplatform.project.vo.ProjectVo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/19
 */

@Data
public class ListOutVo {
    private String id;

    private String projectName;

    private String remarks;

    private String addTime;

    private String updateTime;

    private List<Object> tagList;

    private String rootPath;

}
