package com.collectplatform.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/19
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "project_tag", keepGlobalPrefix = true)
public class ProjectTagEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String projectId;

    private String tag;

}
