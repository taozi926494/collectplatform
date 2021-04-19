package com.collectplatform.project.service;

import com.collectplatform.project.entity.StartTmpl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.collectplatform.project.vo.StartTmplVo.AddVo;
import com.collectplatform.project.vo.StartTmplVo.UpdateVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Taoz
 * @since 2021-04-14
 */
public interface StartTmplService extends IService<StartTmpl> {
    public Long add(AddVo addVo) throws Exception;
    public Integer update(UpdateVo updateVo) throws Exception;
}
