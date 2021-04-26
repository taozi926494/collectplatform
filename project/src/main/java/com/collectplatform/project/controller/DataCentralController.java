package com.collectplatform.project.controller;

import com.collectplatform.core.common.R;
import com.collectplatform.project.service.DataCentralService;
import com.collectplatform.project.vo.DataCentralVo.TotalOutVo;
import com.collectplatform.project.vo.DataCentralVo.WeekSlideOutVo;
import com.collectplatform.project.vo.DataCentralVo.WeekTotalOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("data-central")
public class DataCentralController {
    @Autowired
    private DataCentralService dataCentralService;

    @GetMapping("/total")
    public R<TotalOutVo> total() {
        return new R<TotalOutVo>(dataCentralService.total());
    }

    @GetMapping("/weekTotal")
    public R<List<WeekTotalOutVo>> weekTotal() {
        return new R<List<WeekTotalOutVo>>(dataCentralService.weekTotal());
    }

    @GetMapping("/weekSlide")
    public R<WeekSlideOutVo> weekSlide() {
        return new R<WeekSlideOutVo>(dataCentralService.weekSlide());
    }

}
