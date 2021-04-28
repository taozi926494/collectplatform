package com.collectplatform.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "proj_project", keepGlobalPrefix = true)
public class ProjectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String projectName;

    private String remarks;

    private Date addTime;

    private Date updateTime;


}

