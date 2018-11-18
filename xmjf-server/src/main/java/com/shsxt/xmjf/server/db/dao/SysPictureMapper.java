package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.SysPicture;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPictureMapper extends BaseMapper<SysPicture>{

    public List<SysPicture> querySysPicturesByItemId(@Param("itemId") Integer itemId);

}