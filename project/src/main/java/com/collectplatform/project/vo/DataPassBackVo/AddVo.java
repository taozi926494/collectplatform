package com.collectplatform.project.vo.DataPassBackVo;

import com.collectplatform.core.common.validators.annotation.AssignId;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AddVo {
    @AssignId
    private Long projectId;

    @NotBlank
    private String projectName;

    @NotBlank
    private String jobId;  // 任务id

    @NotBlank
    private String roundId;  // 运行任务id

    private Integer num;  // 采集数量

    private Double fileSize;  // 文件大小

    private String executorIp;  // 执行器ip

    private String jobType;   // 任务类型, 主任务master/从任务slave

    private Integer responseNum;   //命中网页数量

    private Integer requestNum;   // 采集网页数量

    private Integer crawlUrl;   // 采集url数量

}
