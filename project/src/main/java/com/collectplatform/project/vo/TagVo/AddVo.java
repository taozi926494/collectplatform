package com.collectplatform.project.vo.TagVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AddVo {
    @NotBlank
    private String parentId;
    @NotBlank
    private String name;

}
