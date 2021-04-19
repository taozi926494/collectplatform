package com.collectplatform.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.vo.ProjectVo.*;

import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */
public interface ProjectService extends IService<ProjectEntity> {
     String add(AddVo addVo);
     String delete(DeleteVo deleteVo);
     String update(UpdateVo updateVo);
     IPage<ListOutVo> listPage(ListInVo listInVo);
     List<ListOutVo> listAll(String projectName);
}
