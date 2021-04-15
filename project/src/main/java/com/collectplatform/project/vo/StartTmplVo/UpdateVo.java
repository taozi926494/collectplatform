package com.collectplatform.project.vo.StartTmplVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateVo extends AddVo {
    @NotNull
    private Long id;
}
