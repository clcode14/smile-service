package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserCharityFaultMessageLikeEntity
 * @CreateTime 2019/5/26 22:07
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_user_charity_fault_message_like")
public class UserCharityFaultMessageLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "message_id")
    private Integer messageId;
}
