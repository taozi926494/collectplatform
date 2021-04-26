package com.collectplatform.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.entity.DataPassBackEntity;
import com.collectplatform.project.vo.DataCentralVo.TotalOutVo;
import com.collectplatform.project.vo.DataCentralVo.WeekSlideOutVo;
import com.collectplatform.project.vo.DataCentralVo.WeekTotalOutVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Clc
 * @since 2021-04-14
 */
public interface DataCentralService extends IService<DataPassBackEntity> {

    public TotalOutVo total();

    public List<WeekTotalOutVo> weekTotal();

    public WeekSlideOutVo weekSlide();

//    public String delete(DeleteVo deleteVo);
//
//    public String update(LabelEntity labelEntity);
//
//    public IPage<ListOutVo> listPage(ListInVo listInVo);
//
//    public List<ListOutVo> listAll(String name);

}
