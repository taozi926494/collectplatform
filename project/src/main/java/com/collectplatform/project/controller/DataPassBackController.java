package com.collectplatform.project.controller;

import com.collectplatform.core.common.R;
import com.collectplatform.project.service.DataPassBackService;
import com.collectplatform.project.vo.DataPassBackVo.AddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("data-pass-back")
public class DataPassBackController {
    @Autowired
    private DataPassBackService dataPassBackService;

    @RequestMapping("/add")
    public R<Long> add(@RequestBody @Valid AddVo addVo) {
        return new R<Long>(dataPassBackService.add(addVo));
    }

}
