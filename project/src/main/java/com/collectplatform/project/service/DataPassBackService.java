package com.collectplatform.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.entity.DataPassBackEntity;
import com.collectplatform.project.vo.DataPassBackVo.AddVo;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
public interface DataPassBackService extends IService<DataPassBackEntity> {

    public Long add(AddVo addVo);

}
