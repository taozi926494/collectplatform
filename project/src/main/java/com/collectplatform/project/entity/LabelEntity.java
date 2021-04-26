package com.collectplatform.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "proj_label", keepGlobalPrefix = true)
public class LabelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long parentId;

    private String name;

    private Date addTime;

    private Date updateTime;


}
