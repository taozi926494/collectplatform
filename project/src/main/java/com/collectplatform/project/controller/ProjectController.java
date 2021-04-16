package com.collectplatform.project.controller;

import com.collectplatform.core.common.R;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.service.ProjectService;
import com.collectplatform.project.vo.ProjectVo.AddVo;
import com.collectplatform.project.vo.ProjectVo.DeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/add")
    public R<String> add(@RequestBody AddVo addVo) { return new R<String>(projectService.add(addVo));}

    @GetMapping("/update")
    public R<String> update(@RequestBody ProjectEntity projectEntity) {return new R<String>(projectService.update(projectEntity));}

    @GetMapping("/delete")
    public R<String> delete(@Valid DeleteVo deleteVo) { return new R<String>(projectService.delete(deleteVo));}
}
