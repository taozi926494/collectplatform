package com.collectplatform.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.collectplatform.project.entity.LabelEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.vo.LabelVo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
public interface LabelService extends IService<LabelEntity> {

    public String add(AddVo addVo);

    public String delete(DeleteVo deleteVo);

    public String update(LabelEntity labelEntity);

    public IPage<ListOutVo> listPage(ListInVo listInVo);

    public List<ListOutVo> listAll(String name);

}
