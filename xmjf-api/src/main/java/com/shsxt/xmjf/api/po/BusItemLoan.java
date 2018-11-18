package com.shsxt.xmjf.api.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BusItemLoan implements Serializable{

    private static final long serialVersionUID = 4625570956388502585L;
    private Integer id;

    private Integer itemId;

    private String number;

    private String borrower;

    private String idCard;

    private String mobile;

    private String carBrand;

    private String carDemio;

    private String carType;

    private String carColor;

    private String buyTime;

    private String buyPrice;

    private String kilometers;

    private String assessPrice;

    private Date licensingTime;

    private Integer isNewCar;

    private BigDecimal firstPayAmount;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower == null ? null : borrower.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
    }

    public String getCarDemio() {
        return carDemio;
    }

    public void setCarDemio(String carDemio) {
        this.carDemio = carDemio == null ? null : carDemio.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor == null ? null : carColor.trim();
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime == null ? null : buyTime.trim();
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice == null ? null : buyPrice.trim();
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers == null ? null : kilometers.trim();
    }

    public String getAssessPrice() {
        return assessPrice;
    }

    public void setAssessPrice(String assessPrice) {
        this.assessPrice = assessPrice == null ? null : assessPrice.trim();
    }

    public Date getLicensingTime() {
        return licensingTime;
    }

    public void setLicensingTime(Date licensingTime) {
        this.licensingTime = licensingTime;
    }

    public Integer getIsNewCar() {
        return isNewCar;
    }

    public void setIsNewCar(Integer isNewCar) {
        this.isNewCar = isNewCar;
    }

    public BigDecimal getFirstPayAmount() {
        return firstPayAmount;
    }

    public void setFirstPayAmount(BigDecimal firstPayAmount) {
        this.firstPayAmount = firstPayAmount;
    }
}