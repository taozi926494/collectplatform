package com.collectplatform.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.collectplatform.project.dao.ProjectDao;
import com.collectplatform.project.dao.ProjectTagDao;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.entity.ProjectTagEntity;
import com.collectplatform.project.property.ScriptFileProperties;
import com.collectplatform.project.service.ProjectService;
import com.collectplatform.project.util.StringTools;
import com.collectplatform.project.vo.ProjectVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService {
    @Autowired
    private ProjectTagDao projectTagDao;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    ScriptFileProperties scriptFileProperties;

    @Override
    public String add(AddVo addVo) {
        ProjectEntity projectInfo = new ProjectEntity();
        projectInfo.setProjectName(addVo.getProjectName());
        projectInfo.setRemarks(addVo.getRemarks());
        projectDao.insert(projectInfo);
        if (instertTag(addVo.getTagList(), projectInfo.getId())) {
            return projectInfo.getId();
        } else {
            return "创建失败";
        }
    }

    @Override
    public String delete(DeleteVo deleteVo) {
        String filePath = scriptFileProperties.getUploadDir() + "/" + deleteVo.getId();
        File file = new File(filePath);
        if (!file.exists() || delFolder(file)) {
            deleteTag(deleteVo.getId());
            projectDao.deleteById(deleteVo.getId());
            return deleteVo.getId();
        } else {
            return "删除失败";
        }

    }


    @Override
    public String update(UpdateVo updateVo) {
        UpdateWrapper<ProjectEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", updateVo.getId());

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName(updateVo.getProjectName());
        projectEntity.setRemarks(updateVo.getRemarks());
        if (deleteTag(updateVo.getId()) & instertTag(updateVo.getTagList(), updateVo.getId())) {
            projectDao.update(projectEntity, updateWrapper);
            return updateVo.getId();
        } else {
            return "更新失败";
        }
    }

    @Override
    public IPage<ListOutVo> listPage(ListInVo listInVo) {
        Page<ProjectEntity> page = new Page<>(listInVo.getPage(), listInVo.getSize());
        QueryWrapper<ProjectEntity> queryWrapper = new QueryWrapper<>();
        if (!StringTools.isNullOrEmpty(listInVo.getProjectName())) {
            queryWrapper.like("project_name", listInVo.getProjectName());
        }
        return projectDao.listPage(page, queryWrapper);
    }

    @Override
    public List<ListOutVo> listAll(String projectName) {
        QueryWrapper<ProjectEntity> wrapper = new QueryWrapper<>();
        if (!StringTools.isNullOrEmpty(projectName)) {
            wrapper.like("project_name", projectName);
        }
        return projectDao.listAll(wrapper);
    }

    @Override
    public ListOutVo getDetail(String id) {
        QueryWrapper<ProjectEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        ListOutVo detailResult = projectDao.getDetail(wrapper);
        Path targetLocation = Paths.get(scriptFileProperties.getUploadDir(), id);
        detailResult.setRootPath(targetLocation.toString());
        return detailResult;
    }

    //递归删除文件夹
    public boolean delFolder(File folder){
        if (folder.isFile()){
            return folder.delete();
        } else {
            for (File childFile : Objects.requireNonNull(folder.listFiles())){
                delFolder(childFile);
            }
        }
        return folder.delete();
    }

    //批量更新
    public boolean instertTag(List<String> tagList, String project_id) {
        try {
            for (String target : tagList) {
                ProjectTagEntity projectTagEntity = new ProjectTagEntity();
                projectTagEntity.setTagId(target);
                projectTagEntity.setProjectId(project_id);
                projectTagDao.insert(projectTagEntity);
            }
        } catch (Exception e) {
//            System.out.println(e);
            return false;
        }
        return true;
    }

    //删除标签
    public boolean deleteTag(String id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("project_id", id);
        try {
            projectTagDao.deleteByMap(map);
        } catch (Exception e) {
//            System.out.println(e);
            return false;
        }
        return true;
    }

}
