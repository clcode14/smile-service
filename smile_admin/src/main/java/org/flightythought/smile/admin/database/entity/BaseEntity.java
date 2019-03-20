package org.flightythought.smile.admin.database.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 12:59
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * 备注
     */
    @Column(name = "memo")
    protected String memo;

    /**
     * 创建者
     */
    @Column(name = "create_user_name")
    protected String createUserName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @CreationTimestamp
    protected LocalDateTime createTime;

    /**
     * 修改者
     */
    @Column(name = "update_user_name")
    protected String updateUserName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @UpdateTimestamp
    protected LocalDateTime updateTime;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
