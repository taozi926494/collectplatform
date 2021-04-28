package com.collectplatform.project.dao;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.vo.ProjectVo.ListOutVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */
@Component
public interface ProjectDao extends BaseMapper<ProjectEntity> {
    IPage<ListOutVo> listPage(IPage page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    List<ListOutVo> listAll(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    ListOutVo getDetail(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
