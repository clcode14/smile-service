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
    @Column(name = "course_image_id")
    private int courseImageId;

    /**
     * 课程ID
     */
    @Basic
    @Column(name = "course_id")
    private int courseId;

    /**
     * 文件名称
     */
    @Basic
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @Basic
    @Column(name = "path")
    private String path;

    /**
     * 文件大小
     */
    @Basic
    @Column(name = "size", columnDefinition = "int")
    private Long size;
}
