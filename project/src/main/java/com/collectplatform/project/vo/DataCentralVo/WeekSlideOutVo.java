package com.collectplatform.project.vo.DataCentralVo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class WeekSlideOutVo {

    private List<String> projectName;

    private List<String> xAxis;

    private HashMap<String, List<Integer>> yAxis;

}
