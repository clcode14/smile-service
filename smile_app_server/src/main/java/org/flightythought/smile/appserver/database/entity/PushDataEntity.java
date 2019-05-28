package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PushDataEntity
 * @CreateTime 2019/5/27 18:11
 * @Description: TODO
 */
@Entity
@Table(name = "tb_push_data")
@Data
public class PushDataEntity extends BaseEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 数据对象JSON
     */
    @Column(name = "data", columnDefinition = "text")
    private String data;

    /**
     * 用户是否已读
     */
    @Column(name = "is_read")
    private Boolean read;

    /**
     * 推送信息类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 推送信息CODE
     */
    @Column(name = "code", columnDefinition = "enum")
    private String code;
}
