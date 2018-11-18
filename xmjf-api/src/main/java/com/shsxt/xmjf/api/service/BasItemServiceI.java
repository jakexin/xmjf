package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.po.BasItem;
import com.shsxt.xmjf.api.querys.BasItemQuery;
import com.shsxt.xmjf.api.utils.PageList;

public interface BasItemServiceI {
    public PageList queryBasItemsByParams(BasItemQuery basItemQuery);

    public void updateItemToOpen(Integer itemId);

    public BasItem queryBasItemById(Integer itemId);
}
