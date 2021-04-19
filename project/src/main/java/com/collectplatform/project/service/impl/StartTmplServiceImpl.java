package com.collectplatform.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.collectplatform.project.entity.StartTmpl;
import com.collectplatform.project.dao.StartTmplMapper;
import com.collectplatform.project.service.StartTmplService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.collectplatform.project.vo.StartTmplVo.AddVo;
import com.collectplatform.project.vo.StartTmplVo.CategoryVo;
import com.collectplatform.project.vo.StartTmplVo.UpdateVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Taoz
 * @since 2021-04-14
 */
@Service
public class StartTmplServiceImpl extends ServiceImpl<StartTmplMapper, StartTmpl> implements StartTmplService {
    public Long add(AddVo addVo) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        StartTmpl startTmpl = new StartTmpl();
        startTmpl.setName(addVo.getName());
        startTmpl.setConfigs(objectMapper.writeValueAsString(addVo.getConfigs()));

        baseMapper.insert(startTmpl);
        return startTmpl.getId();
    }

    @Override
    public Integer update(UpdateVo updateVo) throws Exception {
        UpdateWrapper<StartTmpl> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", updateVo.getId());

        StartTmpl startTmpl = new StartTmpl();
        startTmpl.setName(updateVo.getName());

        ObjectMapper objectMapper = new ObjectMapper();
        startTmpl.setConfigs(objectMapper.writeValueAsString(updateVo.getConfigs()));
        return baseMapper.update(startTmpl, updateWrapper);
    }
}
