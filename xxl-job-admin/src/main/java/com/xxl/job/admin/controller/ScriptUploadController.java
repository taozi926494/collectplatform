package com.xxl.job.admin.controller;

import com.collectplatform.core.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */
@RestController
@RequestMapping("script-upload")
public class ScriptUploadController {
    @GetMapping("/get")
    public R<String> get() {
        return new R<>("This is XXL-JOB service response");
    }
}
