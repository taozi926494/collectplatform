package com.collectplatform.project.vo.StartTmplVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConfigVo {
    @NotBlank
    private String name;
    @NotBlank
    private String en;
    private String value;
}
