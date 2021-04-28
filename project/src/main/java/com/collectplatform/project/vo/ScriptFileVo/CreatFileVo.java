package com.collectplatform.project.vo.ScriptFileVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/26
 */

@Data
public class CreatFileVo {

    @NotBlank
    private String id;

    @NotBlank
    private String path;

    @NotBlank
    private String fileName;
}
