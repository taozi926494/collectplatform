package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlProjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface XxlPojectInfoDao {

    public List<XxlProjectInfo> findAll();

    public List<XxlProjectInfo> findByAddressType(@Param("addressType") int addressType);

    public int save(XxlProjectInfo xxlJobGroup);

    public int update(XxlProjectInfo xxlJobGroup);

    public int remove(@Param("id") int id);

    public XxlProjectInfo load(@Param("id") int id);

    public List<XxlProjectInfo> pageList(@Param("offset") int offset,
                                         @Param("pagesize") int pagesize,
                                         @Param("projectName") String projectName,
                                         @Param("projectDesc") String projectDesc,
                                         @Param("author") String author);

    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("projectName") String projectName,
                             @Param("projectDesc") String projectDesc,
                             @Param("author") String author);

}
