package com.collectplatform.project.vo.StartTmplVo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryVo {
    @NotBlank
    private String name;
    @NotBlank
    private String en;
    @Valid
    List<ConfigVo> configs;
}
