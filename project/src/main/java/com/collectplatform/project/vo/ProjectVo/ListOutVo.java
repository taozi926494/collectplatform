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

    private Date addTime;

    private Date updateTime;

    private Object tag;

}
