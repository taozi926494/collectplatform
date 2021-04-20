package com.collectplatform.project.vo.TagVo;

import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class ListInVo {

    private String name;
    @NotBlank
    private Integer page;
    @NotBlank
    private Integer size;

}
