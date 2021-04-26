package com.collectplatform.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.collectplatform.core.common.R;
import com.collectplatform.project.dao.DataCentralDao;
import com.collectplatform.project.entity.DataPassBackEntity;
import com.collectplatform.project.service.DataCentralService;
import com.collectplatform.project.vo.DataCentralVo.TotalOutVo;
import com.collectplatform.project.vo.DataCentralVo.DataInfoVo;
import com.collectplatform.project.vo.DataCentralVo.WeekSlideOutVo;
import com.collectplatform.project.vo.DataCentralVo.WeekTotalOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import com.collectplatform.project.util.GetProjectConfig;
import org.springframework.stereotype.Service;
import com.collectplatform.project.util.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
@Service
public class DataCentralServiceImpl extends ServiceImpl<DataCentralDao, DataPassBackEntity> implements DataCentralService {
    @Autowired
    private DataCentralDao dataCentralDao;

    @Autowired
    private GetProjectConfig getProjectConfig;

    @Autowired
    private Tool tool;

    @Override
    public TotalOutVo total(){
        TotalOutVo total = new TotalOutVo();
        DataInfoVo dataInfo = dataCentralDao.dataInfo();  // 数据总量、文件大小
        Integer taskNum = dataCentralDao.taskNum();  // 近24小时任务数
        Double dataSize = dataCentralDao.dataSize(getProjectConfig.getStorageDatabase());  // 数据规模
        total.setDataCount(dataInfo.getDataCount());
        total.setFileSize(dataInfo.getFileSize());
        total.setTaskNum(taskNum);
        total.setDataSize(dataSize + dataInfo.getFileSize());
        return total;
    }

    @Override
    public List<WeekTotalOutVo> weekTotal(){
        List<WeekTotalOutVo> weekTotalList = new ArrayList<>();
        List<String> weekDaysList = tool.getDays(7);
        for (String days: weekDaysList) {
            WeekTotalOutVo weekTotal = new WeekTotalOutVo();
            Integer total = dataCentralDao.weekTotal(days);
            weekTotal.setDataNum(total);
            weekTotal.setDate(days);
            weekTotalList.add(weekTotal);
        }
        return weekTotalList;
    }

    @Override
    public WeekSlideOutVo weekSlide(){
        WeekSlideOutVo weekSlide = new WeekSlideOutVo();
        Integer projectNum = 50;
        List<String> weekDaysList = tool.getDays(7);
        List<String> projectNameList = dataCentralDao.getTopProject(projectNum);
        HashMap<String, List<Integer>> yAxis = new HashMap<>();
        for (String days: weekDaysList) {
            List<Integer> yAxisData = dataCentralDao.getProjectNum(days, projectNameList);
            yAxis.put(days, yAxisData);
        }
        weekSlide.setProjectName(projectNameList);
        weekSlide.setXAxis(weekDaysList);
        weekSlide.setYAxis(yAxis);
        return weekSlide;
    }



}
