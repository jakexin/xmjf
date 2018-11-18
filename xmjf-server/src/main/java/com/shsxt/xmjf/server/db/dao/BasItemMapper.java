package com.shsxt.xmjf.server.db.dao;

import com.shsxt.xmjf.api.po.BasItem;
import com.shsxt.xmjf.server.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BasItemMapper extends BaseMapper<BasItem>{

    public List<Map<String,Object>> queryBasItemsByParams(@Param("itemCycle")Integer itemCycle,@Param("isHistory") Integer isHistory,@Param("itemType") Integer itemType);

    Integer updateItemToOpen(@Param("itemId") Integer itemId);
}