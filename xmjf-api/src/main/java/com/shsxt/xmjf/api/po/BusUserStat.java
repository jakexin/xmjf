package com.shsxt.xmjf.api.po;

import java.math.BigDecimal;

public class BusUserStat extends BusUserStatKey {
    private Integer rechargeCount;

    private BigDecimal rechargeAmount;

    private Integer cashCount;

    private BigDecimal cashAmount;

    private Integer investCount;

    private BigDecimal investAmount;

    private BigDecimal investLaveAmount;

    private Integer couponCount;

    private BigDecimal couponAmount;

    public Integer getRechargeCount() {
        return rechargeCount;
    }

    public void setRechargeCount(Integer rechargeCount) {
        this.rechargeCount = rechargeCount;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Integer getCashCount() {
        return cashCount;
    }

    public void setCashCount(Integer cashCount) {
        this.cashCount = cashCount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Integer getInvestCount() {
        return investCount;
    }

    public void setInvestCount(Integer investCount) {
        this.investCount = investCount;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getInvestLaveAmount() {
        return investLaveAmount;
    }

    public void setInvestLaveAmount(BigDecimal investLaveAmount) {
        this.investLaveAmount = investLaveAmount;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }
}