package com.shsxt.xmjf.server.service;

import com.github.pagehelper.PageHelper;
import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.po.BasItem;
import com.shsxt.xmjf.api.querys.BasItemQuery;
import com.shsxt.xmjf.api.service.BasItemServiceI;
import com.shsxt.xmjf.api.utils.ItemStatus;
import com.shsxt.xmjf.api.utils.PageList;
import com.shsxt.xmjf.server.db.dao.BasItemMapper;
import com.shsxt.xmjf.server.utils.AssertUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BasItemServiceImpl implements BasItemServiceI {

    @Resource
    private BasItemMapper basItemMapper;
    @Override
    public PageList queryBasItemsByParams(BasItemQuery basItemQuery) {
        PageHelper.startPage(basItemQuery.getPageNum(),basItemQuery.getPageSize());
        List<Map<String,Object>> vals= basItemMapper.queryBasItemsByParams(basItemQuery.getItemCycle(),basItemQuery.getIsHistory(),basItemQuery.getItemType());
        if(!(CollectionUtils.isEmpty(vals))){
            for(Map<String,Object> map:vals){
                int status= (int) map.get("item_status");
                if(status== ItemStatus.OPEN){
                    BigDecimal itemAccount= (BigDecimal) map.get("item_account");
                    BigDecimal itemOnGoing= (BigDecimal) map.get("item_ongoing_account");
                    map.put("syAmount",itemAccount.add(itemOnGoing.negate()));
                }

                /**
                 * 等待开放项目
                 *  如果时间小于等于当前时间
                 *    执行状态更新操作
                 */
                if(status==ItemStatus.WAITOPEN){
                    Date relaseTime= (Date) map.get("release_time");
                    map.put("syTime",(relaseTime.getTime()-System.currentTimeMillis())/1000);

                }

            }
        }
        return new PageList(vals);
    }

    @Override
    public void updateItemToOpen(Integer itemId) {
        AssertUtil.isTrue(basItemMapper.updateItemToOpen(itemId)<1, P2pConstant.OPS_ERROR_MSG);
    }

    @Override
    public BasItem queryBasItemById(Integer itemId) {
        return basItemMapper.queryById(itemId);
    }

   /* @Override
    public void updateItemToOpen(Integer itemId) {
        AssertUtil.isTrue(basItemMapper.updateItemToOpen(itemId)<1, P2pConstant.OPS_ERROR_MSG);
    }*/

    public static void main(String[] args) {
        BigDecimal a=BigDecimal.valueOf(100.01);
        BigDecimal b=BigDecimal.valueOf(20.54);
        System.out.println(a.add(b.negate()));
    }
}
