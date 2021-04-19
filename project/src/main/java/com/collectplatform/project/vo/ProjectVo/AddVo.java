package com.collectplatform.project.vo.ProjectVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@Data
public class AddVo {
    @NotBlank
    private String projectName;

    @NotEmpty
    private List<Object> tagList;
}
