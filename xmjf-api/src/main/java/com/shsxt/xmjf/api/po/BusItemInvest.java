package com.shsxt.xmjf.api.po;

import java.math.BigDecimal;
import java.util.Date;

public class BusItemInvest {
    private Integer id;

    private Integer itemId;

    private Integer userId;

    private Integer investType;

    private Integer investCurrent;

    private String investOrder;

    private Integer investStatus;

    private BigDecimal investAmount;

    private BigDecimal investDealAmount;

    private BigDecimal collectAmount;

    private BigDecimal collectPrincipal;

    private BigDecimal collectInterest;

    private BigDecimal actualCollectAmount;

    private BigDecimal actualCollectPrincipal;

    private BigDecimal actualCollectInterest;

    private BigDecimal actualUncollectAmount;

    private BigDecimal actualUncollectPrincipal;

    private BigDecimal actualUncollectInterest;

    private BigDecimal additionalRateAmount;

    private String addip;

    private Date addtime;

    private Date updatetime;

    private String specialMarks;

    private Integer autoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInvestType() {
        return investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    public Integer getInvestCurrent() {
        return investCurrent;
    }

    public void setInvestCurrent(Integer investCurrent) {
        this.investCurrent = investCurrent;
    }

    public String getInvestOrder() {
        return investOrder;
    }

    public void setInvestOrder(String investOrder) {
        this.investOrder = investOrder == null ? null : investOrder.trim();
    }

    public Integer getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(Integer investStatus) {
        this.investStatus = investStatus;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getInvestDealAmount() {
        return investDealAmount;
    }

    public void setInvestDealAmount(BigDecimal investDealAmount) {
        this.investDealAmount = investDealAmount;
    }

    public BigDecimal getCollectAmount() {
        return collectAmount;
    }

    public void setCollectAmount(BigDecimal collectAmount) {
        this.collectAmount = collectAmount;
    }

    public BigDecimal getCollectPrincipal() {
        return collectPrincipal;
    }

    public void setCollectPrincipal(BigDecimal collectPrincipal) {
        this.collectPrincipal = collectPrincipal;
    }

    public BigDecimal getCollectInterest() {
        return collectInterest;
    }

    public void setCollectInterest(BigDecimal collectInterest) {
        this.collectInterest = collectInterest;
    }

    public BigDecimal getActualCollectAmount() {
        return actualCollectAmount;
    }

    public void setActualCollectAmount(BigDecimal actualCollectAmount) {
        this.actualCollectAmount = actualCollectAmount;
    }

    public BigDecimal getActualCollectPrincipal() {
        return actualCollectPrincipal;
    }

    public void setActualCollectPrincipal(BigDecimal actualCollectPrincipal) {
        this.actualCollectPrincipal = actualCollectPrincipal;
    }

    public BigDecimal getActualCollectInterest() {
        return actualCollectInterest;
    }

    public void setActualCollectInterest(BigDecimal actualCollectInterest) {
        this.actualCollectInterest = actualCollectInterest;
    }

    public BigDecimal getActualUncollectAmount() {
        return actualUncollectAmount;
    }

    public void setActualUncollectAmount(BigDecimal actualUncollectAmount) {
        this.actualUncollectAmount = actualUncollectAmount;
    }

    public BigDecimal getActualUncollectPrincipal() {
        return actualUncollectPrincipal;
    }

    public void setActualUncollectPrincipal(BigDecimal actualUncollectPrincipal) {
        this.actualUncollectPrincipal = actualUncollectPrincipal;
    }

    public BigDecimal getActualUncollectInterest() {
        return actualUncollectInterest;
    }

    public void setActualUncollectInterest(BigDecimal actualUncollectInterest) {
        this.actualUncollectInterest = actualUncollectInterest;
    }

    public BigDecimal getAdditionalRateAmount() {
        return additionalRateAmount;
    }

    public void setAdditionalRateAmount(BigDecimal additionalRateAmount) {
        this.additionalRateAmount = additionalRateAmount;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSpecialMarks() {
        return specialMarks;
    }

    public void setSpecialMarks(String specialMarks) {
        this.specialMarks = specialMarks == null ? null : specialMarks.trim();
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }
}