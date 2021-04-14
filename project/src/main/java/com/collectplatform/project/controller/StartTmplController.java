package com.collectplatform.project.controller;


import com.collectplatform.project.entity.StartTmpl;
import com.collectplatform.project.service.StartTmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.collectplatform.core.common.R;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Taoz
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/start-tmpl")
public class StartTmplController {
    @Autowired
    StartTmplService startTmplService;
    
    @GetMapping("all")
    public R<List<StartTmpl>> all() {
        return new R<>(startTmplService.list());
    }

//    @PostMapping("add")
//    public R<Long> add(StartTmpl startTmpl) {
//        return new R<Long>(startTmplService.save(startTmpl))
//    }

}

