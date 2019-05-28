package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserSettingEntity
 * @CreateTime 2019/5/27 2:06
 * @Description: TODO
 */
@Entity
@Table(name = "tb_user_setting")
@Data
public class UserSettingEntity extends BaseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "setting_id")
    private Integer settingId;

    /**
     * CODE
     */
    @Column(name = "code")
    private String code;

    /**
     * Value
     */
    @Column(name = "value")
    private String value;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;
}
