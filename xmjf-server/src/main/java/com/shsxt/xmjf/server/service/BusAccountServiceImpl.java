package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.po.BusAccount;
import com.shsxt.xmjf.api.service.BusAccountServiceI;
import com.shsxt.xmjf.server.db.dao.BusAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusAccountServiceImpl implements BusAccountServiceI {
    @Autowired
    private BusAccountMapper busAccountMapper;

    @Override
    public BusAccount queryBusAccountByUserId(Integer userId) {
        return busAccountMapper.queryBusAccountByUserId(userId);
    }

    @Override
    public Map<String,Object> queryBusAccountInfoByUserId(Integer userId) {
        Map<String,Object> result=new HashMap<String,Object>();
        Map<String,Object>  map=busAccountMapper.queryBusAccountInfoByUserId(userId);
        List<Map<String,Object>> data1=new ArrayList<Map<String,Object>>();
        for(Map.Entry<String,Object> entry:map.entrySet()) {
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("name", entry.getKey());
            temp.put("y", entry.getValue());
            data1.add(temp);
            if(entry.getKey().equals("总资产")){
               result.put("data2",entry.getValue());
            }
        }
        result.put("data1",data1);
        return result;
    }
}
