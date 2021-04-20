package com.collectplatform.project.vo.TagVo;

import lombok.Data;
import lombok.NonNull;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ListOutVo {
    @NonNull
    private String id;
    @NotBlank
    private String parentId;
    @NotBlank
    private String name;
    @NotBlank
    private Date addTime;
    @NotBlank
    private Date updateTime;

}
