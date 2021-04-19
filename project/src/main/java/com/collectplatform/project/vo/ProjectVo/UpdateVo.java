package com.collectplatform.project.vo.ProjectVo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/19
 */

@Data
public class UpdateVo {
    @NotBlank
    private String id;

    @NotBlank
    private String projectName;

    @NotEmpty
    private List<String> tagId;
}
