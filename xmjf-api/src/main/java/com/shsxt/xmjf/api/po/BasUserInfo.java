package com.shsxt.xmjf.api.po;

import java.util.Date;

public class BasUserInfo {
    private Integer id;

    private Integer userId;

    private Date birthday;

    private Integer customerType;

    private String edu;

    private String companyAddress;

    private String homeAddress;

    private String currentAddress;

    private String birthAddress;

    private String idcard1;

    private String idcard2;

    private Integer sex;

    private String remark;

    private String inviteCode;

    private String qqOpenId;

    private String qqNickname;

    private String weChatOpenId;

    private String weChatNickname;

    private String blogOpenId;

    private String blogNickname;

    private String photo;

    private Integer cashLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu == null ? null : edu.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress == null ? null : homeAddress.trim();
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress == null ? null : currentAddress.trim();
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress == null ? null : birthAddress.trim();
    }

    public String getIdcard1() {
        return idcard1;
    }

    public void setIdcard1(String idcard1) {
        this.idcard1 = idcard1 == null ? null : idcard1.trim();
    }

    public String getIdcard2() {
        return idcard2;
    }

    public void setIdcard2(String idcard2) {
        this.idcard2 = idcard2 == null ? null : idcard2.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId == null ? null : qqOpenId.trim();
    }

    public String getQqNickname() {
        return qqNickname;
    }

    public void setQqNickname(String qqNickname) {
        this.qqNickname = qqNickname == null ? null : qqNickname.trim();
    }

    public String getWeChatOpenId() {
        return weChatOpenId;
    }

    public void setWeChatOpenId(String weChatOpenId) {
        this.weChatOpenId = weChatOpenId == null ? null : weChatOpenId.trim();
    }

    public String getWeChatNickname() {
        return weChatNickname;
    }

    public void setWeChatNickname(String weChatNickname) {
        this.weChatNickname = weChatNickname == null ? null : weChatNickname.trim();
    }

    public String getBlogOpenId() {
        return blogOpenId;
    }

    public void setBlogOpenId(String blogOpenId) {
        this.blogOpenId = blogOpenId == null ? null : blogOpenId.trim();
    }

    public String getBlogNickname() {
        return blogNickname;
    }

    public void setBlogNickname(String blogNickname) {
        this.blogNickname = blogNickname == null ? null : blogNickname.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public Integer getCashLimit() {
        return cashLimit;
    }

    public void setCashLimit(Integer cashLimit) {
        this.cashLimit = cashLimit;
    }
}