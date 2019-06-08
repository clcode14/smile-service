package org.flightythought.smile.admin.database.entity;

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
public class UserFollowCourseEntity extends BaseEntity{
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
    
    /**
     * 课程配置
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private CourseRegistrationEntity courseRegistrationEntity;
    
    /**
     * APP用户信息
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity userEntity;
}
