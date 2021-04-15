package com.collectplatform.project.vo.StartTmplVo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class AddVo {
    @NotBlank
    private String name;
    @NotEmpty
    @Valid
    private List<CategoryVo> configs;
}
