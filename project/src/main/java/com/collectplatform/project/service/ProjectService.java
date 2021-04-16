package com.collectplatform.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.vo.ProjectVo.AddVo;
import com.collectplatform.project.vo.ProjectVo.DeleteVo;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */
public interface ProjectService extends IService<ProjectEntity> {
    public String add(AddVo addVo);
    public String delete(DeleteVo deleteVo);
    public String update(ProjectEntity projectEntity);
}
