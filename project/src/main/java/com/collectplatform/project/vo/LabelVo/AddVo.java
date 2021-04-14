package com.collectplatform.project.vo.LabelVo;

import org.jetbrains.annotations.NotNull;
import lombok.Data;

@Data
public class AddVo {
    @NotNull
    private String id;
    @NotNull
    private String prentId;
    @NotNull
    private String name;

}
