package com.collectplatform.project.controller;


import com.collectplatform.core.common.R;
import com.collectplatform.project.entity.LabelEntity;
import com.collectplatform.project.service.LabelService;
import com.collectplatform.project.vo.LabelVo.AddVo;
import com.collectplatform.project.vo.LabelVo.DeleteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

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
public class LabelController {
    @Autowired
    private LabelService labelService;

    @RequestMapping("/add")
    public R<String> add(@RequestBody AddVo addVo){
        return new R<String>(labelService.add(addVo));
    }

    @GetMapping("/delete")
    public R<String> delete(@Valid DeleteVo deleteVo){
        return new R<String>(labelService.delete(deleteVo));
    }

    @RequestMapping("/update")
    public R<String> update(@RequestBody LabelEntity labelEntity){
        return new R<String>(labelService.update(labelEntity));
    }

}

