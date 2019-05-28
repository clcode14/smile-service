package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserFansEntity
 * @CreateTime 2019/5/27 15:26
 * @Description: TODO
 */
@Entity
@Table(name = "tb_user_fans")
@Data
public class UserFansEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 用户
     */
    @Column(name = "from_user_id")
    private Long fromUserId;

    /**
     * 被关注用户
     */
    @Column(name = "to_user_id")
    private Long toUserId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", insertable = false, updatable = false)
    private UserEntity fromUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", insertable = false, updatable = false)
    private UserEntity toUser;
}
