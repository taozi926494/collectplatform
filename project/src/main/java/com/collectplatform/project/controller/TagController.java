package com.collectplatform.project.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.collectplatform.core.common.R;
import com.collectplatform.project.entity.TagEntity;
import com.collectplatform.project.service.TagService;
import com.collectplatform.project.vo.TagVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
@RestController
@RequestMapping("label")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/add")


    public R<String> add(@RequestBody @Valid AddVo addVo){
        return new R<String>(tagService.add(addVo));
    }

    @GetMapping("/delete")
    public R<String> delete(@Valid DeleteVo deleteVo){
        return new R<String>(tagService.delete(deleteVo));
    }

    @RequestMapping("/update")
    public R<String> update(@RequestBody @Valid TagEntity tagEntity){
        return new R<String>(tagService.update(tagEntity));

    }

    @GetMapping("/list")
    public R<IPage<ListOutVo>> list(@Valid ListInVo listInVo) {
        return new R<IPage<ListOutVo>>(tagService.listPage(listInVo));
    }

    @GetMapping("/all")
    public R<List<ListOutVo>> all(@RequestParam(name="name", required=false) String name) {
        return new R<List<ListOutVo>>(tagService.listAll(name));
    }

}

