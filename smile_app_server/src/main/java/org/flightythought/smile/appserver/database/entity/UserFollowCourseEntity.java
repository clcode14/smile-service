package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserFollowCourseEntity
 * @CreateTime 2019/4/13 18:37
 * @Description: TODO
 */
@Entity
@Table(name = "tb_user_follow_course")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserFollowCourseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "id", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 报名者姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 报名者手机号码
     */
    @Column(name = "phone")
    private String phone;
}
