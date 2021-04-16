package com.collectplatform.project.vo.LabelVo;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DeleteVo {
    @NotBlank
    private String id;
    @NotBlank
    private String parentId;
}
