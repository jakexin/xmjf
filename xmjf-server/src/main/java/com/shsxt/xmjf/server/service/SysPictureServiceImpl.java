package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.po.SysPicture;
import com.shsxt.xmjf.api.service.SysPictureServiceI;
import com.shsxt.xmjf.server.db.dao.SysPictureMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPictureServiceImpl implements SysPictureServiceI {

    @Resource
    private SysPictureMapper sysPictureMapper;
    @Override
    public List<SysPicture> querySysPicturesByItemId(Integer itemId) {
        return sysPictureMapper.querySysPicturesByItemId(itemId);
    }
}
