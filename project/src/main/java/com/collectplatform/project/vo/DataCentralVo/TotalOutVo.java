package com.collectplatform.project.vo.DataCentralVo;

import lombok.Data;

import java.util.Date;

@Data
public class TotalOutVo {

    private Integer dataCount;   //数据总量

    private Double dataSize;   // 数据规模

    private Double fileSize;   // 文件大小

    private Integer taskNum;   // 近24小时任务量

}
