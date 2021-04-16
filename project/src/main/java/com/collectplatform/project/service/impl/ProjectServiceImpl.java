package com.collectplatform.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.collectplatform.project.dao.ProjectDao;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.service.ProjectService;
import com.collectplatform.project.vo.ProjectVo.AddVo;
import com.collectplatform.project.vo.ProjectVo.DeleteVo;
import org.springframework.stereotype.Service;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService{
    @Override
    public String add(AddVo addVo) {
        ProjectEntity projectInfo = new ProjectEntity();
        projectInfo.setProjectName(addVo.getProjectName());
        projectInfo.setFileName(addVo.getFileName());
        projectInfo.setFilePath(addVo.getProjectName() + "/" + addVo.getFileName());
        baseMapper.insert(projectInfo);
        return projectInfo.getId();
    }

    @Override
    public String delete(DeleteVo deleteVo) {
        baseMapper.deleteById(deleteVo.getId());
        return deleteVo.getId();
    }


    @Override
    public String update(ProjectEntity projectEntity) {
        baseMapper.updateById(projectEntity);
        return projectEntity.getId();
    }
}
