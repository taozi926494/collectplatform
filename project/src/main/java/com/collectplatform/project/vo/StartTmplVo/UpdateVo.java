package com.collectplatform.project.vo.StartTmplVo;

import com.collectplatform.core.common.validators.annotation.AssignId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateVo extends AddVo {
    @AssignId
    private Long id;
}
