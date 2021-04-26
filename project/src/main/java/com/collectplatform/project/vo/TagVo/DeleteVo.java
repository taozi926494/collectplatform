package com.collectplatform.project.vo.TagVo;

import com.collectplatform.core.common.validators.annotation.AssignId;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeleteVo {
    @AssignId
    private Long id;
    @NotNull
    private Long parentId;
}
