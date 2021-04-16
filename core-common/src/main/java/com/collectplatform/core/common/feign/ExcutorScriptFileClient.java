package com.collectplatform.core.common.feign;

import com.collectplatform.core.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */
@Resource
@FeignClient(name="xxl-job-admin")
public interface ExcutorScriptFileClient {
    @GetMapping(value = "/script-upload/get")
    public R<String> scriptUpload();
}
