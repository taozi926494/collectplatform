package com.collectplatform.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.collectplatform.project.dao.DataPassBackDao;
import com.collectplatform.project.entity.DataPassBackEntity;
import com.collectplatform.project.service.DataPassBackService;
import com.collectplatform.project.vo.DataPassBackVo.AddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
@Service
public class DataPassBackServiceImpl extends ServiceImpl<DataPassBackDao, DataPassBackEntity> implements DataPassBackService {
    @Autowired
    private DataPassBackDao dataPassBackDao;

    @Override
    public Long add(AddVo addVo){
        DataPassBackEntity data = new DataPassBackEntity();
        data.setProjectId(addVo.getProjectId());
        data.setProjectName(addVo.getProjectName());
        data.setJobId(addVo.getJobId());
        data.setRoundId(addVo.getRoundId());
        data.setNum(addVo.getNum());
        data.setFileSize(addVo.getFileSize());
        data.setExecutorIp(addVo.getExecutorIp());
        data.setRequestNum(addVo.getRequestNum());
        data.setResponseNum(addVo.getResponseNum());
        data.setJobType(addVo.getJobType());
        data.setCrawlUrl(addVo.getCrawlUrl());
        dataPassBackDao.insert(data);
        return data.getId();
    }

}
