package com.collectplatform.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Clc
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "data_pass_back", keepGlobalPrefix = true)
public class DataPassBackEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long projectId;

    private String projectName;

    private String jobId;  // 任务id

    private String roundId;  // 运行任务id

    private Integer num;  // 采集数量

    private Double fileSize;  // 文件大小

    private String executorIp;  // 执行器ip

    private String jobType;   // 任务类型, 主任务master/从任务slave

    private Integer responseNum;   //命中网页数量

    private Integer requestNum;   // 采集网页数量

    private Integer crawlUrl;   // 采集url数量

    private Date addTime;

    private Date updateTime;


}
