package com.collectplatform.project.vo.ProjectVo;

import lombok.Data;
import lombok.NonNull;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/16
 */

@Data
public class AddVo {
    @NonNull
    private String projectName;

    @NonNull
    private String fileName;
}
