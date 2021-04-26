package com.collectplatform.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collectplatform.project.entity.DataPassBackEntity;
import com.collectplatform.project.vo.DataCentralVo.DataInfoVo;
import com.collectplatform.project.vo.DataCentralVo.WeekTotalOutVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
@Component("DataCentralDao")
public interface DataCentralDao extends BaseMapper<DataPassBackEntity> {
    DataInfoVo dataInfo();

    Integer taskNum();

    Double dataSize(@Param(value="storageDatabase") String storageDatabase);

    Integer weekTotal(@Param(value="days") String days);

    List<String> getTopProject(@Param(value="projectNum") Integer projectNum);

    List<Integer> getProjectNum(@Param(value="days") String days,
                                @Param(value="projectNameList") List<String> projectNameList);

}
