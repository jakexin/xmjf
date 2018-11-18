package com.shsxt.xmjf.api.service;

import com.shsxt.xmjf.api.po.SysPicture;

import java.util.List;

public interface SysPictureServiceI {
    public List<SysPicture> querySysPicturesByItemId( Integer itemId);
}
