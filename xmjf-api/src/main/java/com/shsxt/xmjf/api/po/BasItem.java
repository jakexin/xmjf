package com.shsxt.xmjf.api.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BasItem implements Serializable{
    private static final long serialVersionUID = 5856826907758485539L;
    private Integer id;

    private Byte itemIsalive;

    private Byte itemIsnew;

    private Integer itemIsrecommend;

    private Integer itemStatus;

    private Integer itemUserId;

    private String itemName;

    private String itemSecondName;

    private String itemNumber;

    private String itemDesc;

    private BigDecimal itemRate;

    private BigDecimal itemAddRate;

    private BigDecimal itemServeRate;

    private Integer itemType;

    private Integer itemCycle;

    private Integer itemCycleUnit;

    private BigDecimal itemAccount;

    private BigDecimal itemOngoingAccount;

    private BigDecimal itemScale;

    private BigDecimal itemSingleMinInvestment;

    private Integer itemRepayMethod;

    private BigDecimal itemSingleMaxInvestment;

    private BigDecimal itemMaxInvestment;

    private Integer investTimes;

    private String addip;

    private Date addtime;

    private Date releaseTime;

    private Date endTime;

    private Date updateTime;

    private String password;

    private Integer guaranteeId;

    private String contract;

    private Integer receiptMethod;

    private Integer prepayment;

    private Integer moveVip;

    private String itemLoanUse;

    private Integer itemIsAuto;

    private String itemContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getItemIsalive() {
        return itemIsalive;
    }

    public void setItemIsalive(Byte itemIsalive) {
        this.itemIsalive = itemIsalive;
    }

    public Byte getItemIsnew() {
        return itemIsnew;
    }

    public void setItemIsnew(Byte itemIsnew) {
        this.itemIsnew = itemIsnew;
    }

    public Integer getItemIsrecommend() {
        return itemIsrecommend;
    }

    public void setItemIsrecommend(Integer itemIsrecommend) {
        this.itemIsrecommend = itemIsrecommend;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getItemUserId() {
        return itemUserId;
    }

    public void setItemUserId(Integer itemUserId) {
        this.itemUserId = itemUserId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemSecondName() {
        return itemSecondName;
    }

    public void setItemSecondName(String itemSecondName) {
        this.itemSecondName = itemSecondName == null ? null : itemSecondName.trim();
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber == null ? null : itemNumber.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public BigDecimal getItemRate() {
        return itemRate;
    }

    public void setItemRate(BigDecimal itemRate) {
        this.itemRate = itemRate;
    }

    public BigDecimal getItemAddRate() {
        return itemAddRate;
    }

    public void setItemAddRate(BigDecimal itemAddRate) {
        this.itemAddRate = itemAddRate;
    }

    public BigDecimal getItemServeRate() {
        return itemServeRate;
    }

    public void setItemServeRate(BigDecimal itemServeRate) {
        this.itemServeRate = itemServeRate;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getItemCycle() {
        return itemCycle;
    }

    public void setItemCycle(Integer itemCycle) {
        this.itemCycle = itemCycle;
    }

    public Integer getItemCycleUnit() {
        return itemCycleUnit;
    }

    public void setItemCycleUnit(Integer itemCycleUnit) {
        this.itemCycleUnit = itemCycleUnit;
    }

    public BigDecimal getItemAccount() {
        return itemAccount;
    }

    public void setItemAccount(BigDecimal itemAccount) {
        this.itemAccount = itemAccount;
    }

    public BigDecimal getItemOngoingAccount() {
        return itemOngoingAccount;
    }

    public void setItemOngoingAccount(BigDecimal itemOngoingAccount) {
        this.itemOngoingAccount = itemOngoingAccount;
    }

    public BigDecimal getItemScale() {
        return itemScale;
    }

    public void setItemScale(BigDecimal itemScale) {
        this.itemScale = itemScale;
    }

    public BigDecimal getItemSingleMinInvestment() {
        return itemSingleMinInvestment;
    }

    public void setItemSingleMinInvestment(BigDecimal itemSingleMinInvestment) {
        this.itemSingleMinInvestment = itemSingleMinInvestment;
    }

    public Integer getItemRepayMethod() {
        return itemRepayMethod;
    }

    public void setItemRepayMethod(Integer itemRepayMethod) {
        this.itemRepayMethod = itemRepayMethod;
    }

    public BigDecimal getItemSingleMaxInvestment() {
        return itemSingleMaxInvestment;
    }

    public void setItemSingleMaxInvestment(BigDecimal itemSingleMaxInvestment) {
        this.itemSingleMaxInvestment = itemSingleMaxInvestment;
    }

    public BigDecimal getItemMaxInvestment() {
        return itemMaxInvestment;
    }

    public void setItemMaxInvestment(BigDecimal itemMaxInvestment) {
        this.itemMaxInvestment = itemMaxInvestment;
    }

    public Integer getInvestTimes() {
        return investTimes;
    }

    public void setInvestTimes(Integer investTimes) {
        this.investTimes = investTimes;
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

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(Integer guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public Integer getReceiptMethod() {
        return receiptMethod;
    }

    public void setReceiptMethod(Integer receiptMethod) {
        this.receiptMethod = receiptMethod;
    }

    public Integer getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(Integer prepayment) {
        this.prepayment = prepayment;
    }

    public Integer getMoveVip() {
        return moveVip;
    }

    public void setMoveVip(Integer moveVip) {
        this.moveVip = moveVip;
    }

    public String getItemLoanUse() {
        return itemLoanUse;
    }

    public void setItemLoanUse(String itemLoanUse) {
        this.itemLoanUse = itemLoanUse == null ? null : itemLoanUse.trim();
    }

    public Integer getItemIsAuto() {
        return itemIsAuto;
    }

    public void setItemIsAuto(Integer itemIsAuto) {
        this.itemIsAuto = itemIsAuto;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent == null ? null : itemContent.trim();
    }
}