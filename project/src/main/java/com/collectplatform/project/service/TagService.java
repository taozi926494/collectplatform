package com.collectplatform.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.collectplatform.project.entity.TagEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.vo.TagVo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
public interface TagService extends IService<TagEntity> {

    String add(AddVo addVo);

    String delete(DeleteVo deleteVo);

    String update(TagEntity tagEntity);

    IPage<ListOutVo> listPage(ListInVo listInVo);

    List<ListOutVo> listAll(String name);

}
