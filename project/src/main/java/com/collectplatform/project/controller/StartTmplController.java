package com.collectplatform.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.collectplatform.project.entity.StartTmpl;
import com.collectplatform.project.service.StartTmplService;
import com.collectplatform.project.vo.StartTmplVo.AddVo;
import com.collectplatform.project.vo.StartTmplVo.UpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.collectplatform.core.common.R;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Taoz
 * @since 2021-04-14
 */
@Validated
@RestController
@RequestMapping("/start-tmpl")
public class StartTmplController {
    @Autowired
    StartTmplService startTmplService;

    @GetMapping("get")
    public R<StartTmpl> get(@RequestParam @NotNull Long id) {
        QueryWrapper<StartTmpl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return new R<>(startTmplService.getOne(queryWrapper));
    }

    
    @GetMapping("all")
    public R<List<StartTmpl>> all() {
        return new R<>(startTmplService.list());
    }

    @PostMapping("add")
    public R<Long> add(@Valid @RequestBody AddVo addVo) throws Exception {
        return new R<>(startTmplService.add(addVo));
    }

    @PostMapping("update")
    public R<Integer> update(@RequestBody @Valid UpdateVo updateVo) throws Exception {
        return new R<>(startTmplService.update(updateVo));
    }
}

