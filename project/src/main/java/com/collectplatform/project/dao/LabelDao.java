package com.collectplatform.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.collectplatform.project.entity.LabelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collectplatform.project.vo.LabelVo.ListOutVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
public interface LabelDao extends BaseMapper<LabelEntity> {
    IPage<ListOutVo> listPage(IPage page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    List<ListOutVo> listAll(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    ListOutVo labelInfoById(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    List<ListOutVo> labelInfoByParentId(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);


}
