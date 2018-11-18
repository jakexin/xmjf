package com.shsxt.xmjf.server.service;

import com.shsxt.xmjf.api.constant.P2pConstant;
import com.shsxt.xmjf.api.po.*;
import com.shsxt.xmjf.api.service.BusItemInvestServiceI;
import com.shsxt.xmjf.api.utils.ItemStatus;
import com.shsxt.xmjf.server.db.dao.*;
import com.shsxt.xmjf.server.utils.AssertUtil;
import com.shsxt.xmjf.server.utils.MD5;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BusItemInvestServiceImpl implements BusItemInvestServiceI {

    @Resource
    private BasUserSecurityMapper basUserSecurityMapper;

    @Resource
    private BasItemMapper basItemMapper;

    @Resource
    private BusItemInvestMapper busItemInvestMapper;

    @Resource
    private BusAccountMapper busAccountMapper;

    @Resource
    private BusUserStatMapper busUserStatMapper;

    @Resource
    private BusAccountLogMapper busAccountLogMapper;

    @Resource
    private BusIncomeStatMapper busIncomeStatMapper;

    @Resource
    private BusUserIntegralMapper busUserIntegralMapper;

    @Resource
    private BusIntegralLogMapper busIntegralLogMapper;


    @Override
    public void addBusItemInvest(BigDecimal amount, Integer itemId, Integer userId, String payPwd) {
        /**
         * bas_item	项目表
         bus_item_invest	项目投资表
         bus_user_stat	用户统计表
         bus_account	用户账户表
         bus_account_log	用户账户操作日志表
         bus_income_stat	用户收益信息表
         bus_user_integral	用户积分表
         bus_integral_log	积分操作日志表
         */


        /**
         * 投资接口实现思路:
         *  1.参数基本校验
         *     参数非空  金额 用户id 项目id 支付密码
         *    交易密码比对 必须一致
         *    itemId(项目记录)
         *      必须存在
         *      项目状态必须为开放状态  (项目百分比 100%)
         *      项目类型 新手标仅限首投!!!
         *      移动端pc 端不能投资!!!
         *      是否为定向标(定向标暂不支持)
         *      判断项目剩余金额 是否小于项目最小投资(最小投资存在情况)
         *    amount 投资金额
         *       >0
         *       必须不大于账户可用金额
         *       项目最小投资存在时: 投资金额amount>=最小投资
         *       项目最大投资存在时:投资金额<=最大投资
         *       判断项目剩余金额 是否小于项目最小投资(最小投资存在情况)
         *     userId
         *        必须实名认证
         * 2.执行表数据更新
         *     bas_item	项目表
         *       进度
         *       进行中金额
         *       投资次数
         *       状态
         *       更新时间
              bus_item_invest	项目投资表
                 添加投资记录(投资人id 投资金额 投资时间 利息 实际已收  实际未收...)
             bus_user_stat	用户统计表
                投资总次数
                投资总金额
             bus_account	用户账户表
               总金额
               可用金额
               可提现金额
              冻结金额
            bus_account_log	用户账户操作日志表
              添加账户日志记录
            bus_income_stat	用户收益信息表
              总收益
              已转收益
              代收收益
            bus_user_integral	用户积分表
             总积分
             可用积分
            bus_integral_log	积分操作日志表
             添加积分日志
         */
        AssertUtil.isTrue(null==amount,"请输入投资金额!");
        AssertUtil.isTrue(amount.compareTo(BigDecimal.ZERO)<=0,"投资金额必须大于0!");
        AssertUtil.isTrue(null==itemId,"投资项目记录id不存在");
        AssertUtil.isTrue(StringUtils.isBlank(payPwd),"交易密码不能为空!");
        BasUserSecurity basUserSecurity=basUserSecurityMapper.queryUserSecurityByUserId(userId);
        AssertUtil.isTrue(!(basUserSecurity.getRealnameStatus().equals(1)),"用户未实名,请先执行实名认证操作!");
        AssertUtil.isTrue(!(basUserSecurity.getPaymentPassword().equals(MD5.toMD5(payPwd))),"交易密码不正确!");
        BasItem basItem=basItemMapper.queryById(itemId);
        AssertUtil.isTrue(null==basItem,"待投资的记录不存在!");
        AssertUtil.isTrue(!(basItem.getItemStatus().equals(ItemStatus.OPEN)),"项目状态非法!");
        AssertUtil.isTrue(null!=busItemInvestMapper.queryIsInvestNewItem(userId),"新手标仅限首投!");
        AssertUtil.isTrue(basItem.getMoveVip().equals(1),"PC端不能投资移动端项目!");
        AssertUtil.isTrue(StringUtils.isNoneBlank(basItem.getPassword()),"定向标暂不支开放!");
        BigDecimal singleMinInvestment=basItem.getItemSingleMinInvestment();
        // 计算项目剩余金额
        BigDecimal syAmount=basItem.getItemAccount().add(basItem.getItemOngoingAccount().negate());
        // 最小投资存在时
        if(singleMinInvestment.compareTo(BigDecimal.ZERO)>1){
            AssertUtil.isTrue(syAmount.compareTo(singleMinInvestment)==-1,"项目即将进行截标操作,不能进行投资!");
            AssertUtil.isTrue(amount.compareTo(singleMinInvestment)==-1,"投资金额不能小于最小投资!");
        }
        BusAccount busAccount=busAccountMapper.queryBusAccountByUserId(userId);
        AssertUtil.isTrue(amount.compareTo(busAccount.getUsable())==1,"投资金额不能大于账户可用金额!");
        BigDecimal singleMaxInvestment=basItem.getItemSingleMaxInvestment();
        if(singleMaxInvestment.compareTo(BigDecimal.ZERO)==1){
            AssertUtil.isTrue(amount.compareTo(singleMaxInvestment)==1,"投资金额不能大于单笔最大投资!");
        }

        /**
         * 表更新操作
         */

        /**
         * 项目字段更新
         * *bas_item	项目表
         *       进度
         *       进行中金额
         *       投资次数
         *       状态
         *       更新时间
         */
        basItem.setItemOngoingAccount(basItem.getItemOngoingAccount().add(amount));
        basItem.setInvestTimes(basItem.getInvestTimes()+1);
        basItem.setItemScale(basItem.getItemOngoingAccount().divide(basItem.getItemAccount(),4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
        if(basItem.getItemOngoingAccount().compareTo(basItem.getItemAccount())==0){
            basItem.setItemStatus(ItemStatus.FULL_COMPLETE);
        }
        basItem.setUpdateTime(new Date());
        AssertUtil.isTrue(basItemMapper.update(basItem)<1, P2pConstant.OPS_ERROR_MSG);
        /**
         * bus_item_invest	项目投资表
            添加投资记录(投资人id 投资金额 投资时间 利息 实际已收  实际未收...)
         */
        BusItemInvest busItemInvest=new BusItemInvest();
        busItemInvest.setActualCollectAmount(BigDecimal.ZERO);
        busItemInvest.setActualCollectInterest(BigDecimal.ZERO);
        busItemInvest.setActualCollectPrincipal(BigDecimal.ZERO);
        BigDecimal ll=(basItem.getItemRate().add(basItem.getItemAddRate())).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
        BigDecimal lx=amount.multiply(ll).multiply(BigDecimal.valueOf(basItem.getItemCycle()).divide(BigDecimal.valueOf(365),4, RoundingMode.HALF_UP)).divide(BigDecimal.valueOf(1),2,RoundingMode.HALF_UP);
        busItemInvest.setActualUncollectAmount(amount.add(lx));
        busItemInvest.setActualUncollectInterest(lx);
        busItemInvest.setActualUncollectPrincipal(amount);
        busItemInvest.setAdditionalRateAmount(BigDecimal.ZERO);
        busItemInvest.setAddtime(new Date());
        busItemInvest.setCollectInterest(lx);
        busItemInvest.setCollectAmount(amount.add(lx));
        busItemInvest.setCollectPrincipal(amount);
        busItemInvest.setInvestAmount(amount);
        busItemInvest.setInvestCurrent(1);//定期
        busItemInvest.setInvestDealAmount(amount);
        String investOrder="SXT_TZ_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        busItemInvest.setInvestOrder(investOrder);
        busItemInvest.setInvestStatus(0);
        busItemInvest.setInvestType(1);
        busItemInvest.setItemId(itemId);
        busItemInvest.setUpdatetime(new Date());
        busItemInvest.setUserId(userId);
        AssertUtil.isTrue(busItemInvestMapper.save(busItemInvest)<1,P2pConstant.OPS_ERROR_MSG);

        /**
         * 更新用户统计表信息
         */
        BusUserStat busUserStat=busUserStatMapper.queryBusUserStatByUserId(userId);
        busUserStat.setInvestCount(busItemInvest.getInvestCurrent()+1);
        busUserStat.setInvestAmount(busItemInvest.getInvestAmount().add(amount));
        AssertUtil.isTrue(busUserStatMapper.update(busUserStat)<1,P2pConstant.OPS_ERROR_MSG);

        /**
         * 更新账户金额信息
         */
        busAccount.setTotal(busAccount.getTotal().add(lx));// 原始总金额+投资产生的利息
        busAccount.setCash(busAccount.getCash().add(amount.negate()));
        busAccount.setUsable(busAccount.getUsable().add(amount.negate()));
        busAccount.setWait(busAccount.getWait().add(amount));// 原始代收金额+投资金额
        busAccount.setFrozen(busAccount.getFrozen().add(amount));// 原始冻结金额+投资金额
        AssertUtil.isTrue(busAccountMapper.update(busAccount)<1,P2pConstant.OPS_ERROR_MSG);
        /**
         * 记录账户变动日志
         */
        BusAccountLog busAccountLog=new BusAccountLog();
        busAccountLog.setUserId(userId);
        busAccountLog.setWait(busAccount.getWait());
        busAccountLog.setUsable(busAccount.getUsable());
        busAccountLog.setTotal(busAccount.getTotal());
        busAccountLog.setOperType("用户投资");
        busAccountLog.setBudgetType(2);// 投资操作 设置为支出
        busAccountLog.setRepay(busAccount.getRepay());
        busAccountLog.setRemark("用户投资操作");
        busAccountLog.setOperMoney(amount);
        busAccountLog.setFrozen(busAccount.getFrozen());
        busAccountLog.setAddtime(new Date());
        busAccountLog.setCash(busAccount.getCash());
        AssertUtil.isTrue(busAccountLogMapper.save(busAccountLog)<1,P2pConstant.OPS_ERROR_MSG);

        /**
         * 收益记录更新
         */
        BusIncomeStat busIncomeStat=busIncomeStatMapper.queryBusIncomeStatByUserId(userId);
        busIncomeStat.setWaitIncome(busIncomeStat.getWaitIncome().add(lx));
        busIncomeStat.setTotalIncome(busIncomeStat.getTotalIncome().add(lx));
        AssertUtil.isTrue(busIncomeStatMapper.update(busIncomeStat)<1,P2pConstant.OPS_ERROR_MSG);

        /**
         * 查询积分记录
         */
        BusUserIntegral busUserIntegral=busUserIntegralMapper.queryBusUserIntegralByUserId(userId);
        busUserIntegral.setTotal(busUserIntegral.getTotal()+200);
        busUserIntegral.setUsable(busUserIntegral.getUsable()+200);
        AssertUtil.isTrue(busUserIntegralMapper.update(busUserIntegral)<1,P2pConstant.OPS_ERROR_MSG);

        /**
         * 添加积分日志记录
         */
        BusIntegralLog busIntegralLog=new BusIntegralLog();
        busIntegralLog.setAddtime(new Date());
        busIntegralLog.setIntegral(200);
        busIntegralLog.setStatus(0);// 收入
        busIntegralLog.setUserId(userId);
        busIntegralLog.setWay("用户投资");
        AssertUtil.isTrue(busIntegralLogMapper.save(busIntegralLog)<1,P2pConstant.OPS_ERROR_MSG);


    }

    @Override
    public Map<String, Object> queryFiveMonthInvestInfo(Integer userId) {
        Map<String,Object> result=new HashMap<String,Object>();
        List<Map<String,Object>> vals= busItemInvestMapper.queryFiveMonthInvestInfo(userId);
        List<String> months=new ArrayList<String>();
        List<Object> amounts=new ArrayList<Object>();
        for(Map<String,Object> map:vals){
           months.add((String) map.get("month"));
           amounts.add(map.get("total"));
        }
        result.put("data1",months);
        result.put("data2",amounts);
        return result;
    }


    public static void main(String[] args) {
        /*BigDecimal a=BigDecimal.valueOf(3);
        BigDecimal b=BigDecimal.valueOf(2);
        //System.out.println(b.divide(a, MathContext.DECIMAL128));
        System.out.println(b.divide(a,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
*/
        /**
         * 支付宝
         *    4%  10000  -->400
         *    4%   1000  -->40
         *  利息计算公式
         *    本金*利率*(天数/365)
         *    10000*4%*1=400
         *    1000*4%   40
         */
       /* BigDecimal amount=BigDecimal.valueOf(10000);
        int day=30;
        BigDecimal ll=BigDecimal.valueOf(0.04);
       BigDecimal lx= amount.multiply(ll).multiply(BigDecimal.valueOf(day).divide(BigDecimal.valueOf(365),4, RoundingMode.HALF_UP)).divide(BigDecimal.valueOf(1),2,RoundingMode.HALF_UP);
        System.out.println(lx);*/

        BigDecimal ll=BigDecimal.valueOf(10).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
        System.out.println(ll);
        System.out.println(BigDecimal.valueOf(100).multiply(ll).multiply(BigDecimal.valueOf(30).divide(BigDecimal.valueOf(365),4, RoundingMode.HALF_UP)).divide(BigDecimal.valueOf(1),2,RoundingMode.HALF_UP));

    }
}
