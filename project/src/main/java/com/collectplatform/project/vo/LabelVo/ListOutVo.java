package com.collectplatform.project.vo.LabelVo;

import com.collectplatform.core.common.validators.annotation.AssignId;
import lombok.Data;
import lombok.NonNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ListOutVo {
    @AssignId
    private Long id;
    @NotNull
    private Long parentId;
    @NotBlank
    private String name;
    @NotBlank
    private Date addTime;
    @NotBlank
    private Date updateTime;

}
