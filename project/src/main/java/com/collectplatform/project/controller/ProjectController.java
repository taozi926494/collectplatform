package com.collectplatform.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.collectplatform.core.common.R;
import com.collectplatform.project.entity.ProjectEntity;
import com.collectplatform.project.service.ProjectService;
import com.collectplatform.project.vo.ProjectVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public R<String> add(@Valid @RequestBody AddVo addVo) { return new R<String>(projectService.add(addVo));}

    @PostMapping("/update")
    public R<String> update(@Valid @RequestBody UpdateVo updateVo) {return new R<String>(projectService.update(updateVo));}

    @GetMapping("/delete")
    public R<String> delete(@Valid DeleteVo deleteVo) { return new R<String>(projectService.delete(deleteVo));}

    @GetMapping("/list")
    public R<IPage<ListOutVo>> list(@Valid ListInVo listInVo) {
        return new R<IPage<ListOutVo>>(projectService.listPage(listInVo));
    }
}
