package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseImageEntity.java
 * @CreateTime 2019/3/27 17:12
 * @Description: 课程图片实体类
 */
@Entity
@Table(name = "tb_course_image")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseImageEntity extends BaseEntity {
    /**
     * 课程图片ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 课程ID
     */
    @Basic
    @Column(name = "image_id")
    private Integer courseImageId;

    /**
     * 文件名称
     */
    @Basic
    @Column(name = "course_id")
    private Integer courseId;
}
