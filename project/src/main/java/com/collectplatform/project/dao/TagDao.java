package com.collectplatform.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.collectplatform.project.entity.TagEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collectplatform.project.vo.TagVo.ListOutVo;
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

@Component
public interface TagDao extends BaseMapper<TagEntity> {
    IPage<ListOutVo> listPage(IPage page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    List<ListOutVo> listAll(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    ListOutVo labelInfoById(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    List<ListOutVo> labelInfoByParentId(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);


}
