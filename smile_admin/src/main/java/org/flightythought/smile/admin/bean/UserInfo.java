package org.flightythought.smile.admin.bean;

import org.flightythought.smile.admin.database.entity.SysUserEntity;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 * 登录用户信息
 * @author LiLei
 * @date 2019/1/21 11:59
 */
public class UserInfo {
    /**
     * 登录用户名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 电话号码
     */
    private String tel;

    public UserInfo() {

    }

    public UserInfo(SysUserEntity sysUserEntity) {
        this.loginName = sysUserEntity.getLoginName();
        this.userName = sysUserEntity.getUserName();
        this.email = sysUserEntity.getEmail();
        this.mobilePhone = sysUserEntity.getMobilePhone();
        this.tel = sysUserEntity.getTel();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
