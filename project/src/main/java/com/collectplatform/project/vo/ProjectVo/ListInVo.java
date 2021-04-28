package com.collectplatform.project.vo.ProjectVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/19
 */

@Data
public class ListInVo {

    private String projectName;
    private Integer page;
    private Integer size;
}
