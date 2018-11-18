package com.shsxt.xmjf.api.model;

import java.io.Serializable;

public class UserModel implements Serializable{
    private static final long serialVersionUID = 6509997301433052225L;
    private Integer id;
    private String phone;
    private String userName;

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
