package com.collectplatform.project.service.impl;

import com.collectplatform.project.entity.LabelEntity;
import com.collectplatform.project.dao.LabelDao;
import com.collectplatform.project.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.collectplatform.project.vo.LabelVo.AddVo;
import com.collectplatform.project.vo.LabelVo.DeleteVo;
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
public class LabelServiceImpl extends ServiceImpl<LabelDao, LabelEntity> implements LabelService {
    @Autowired
    private LabelDao labelDao;

    @Override
    public String add(AddVo addVo){
        LabelEntity labelInfo = new LabelEntity();
        labelInfo.setParentId(addVo.getPrentId());
        labelInfo.setName(addVo.getName());
        labelDao.insert(labelInfo);
        return labelInfo.getId();
    }

    @Override
    public String delete(DeleteVo deleteVo){
        labelDao.deleteById(deleteVo.getId());
        return deleteVo.getId();
    }

    @Override
    public String update(LabelEntity labelEntity){
        labelDao.updateById(labelEntity);
        return labelEntity.getId();
    }

}
