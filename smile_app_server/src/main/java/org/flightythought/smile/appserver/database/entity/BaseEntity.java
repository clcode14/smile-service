package org.flightythought.smile.appserver.database.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 12:59
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    protected LocalDateTime updateTime;

}
