package org.flightythought.smile.admin.database.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName BaseEntity.java
 * @CreateTime 2019/3/27 17:11
 * @Description: 基础实体类
 */
@MappedSuperclass
@Data
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
}
