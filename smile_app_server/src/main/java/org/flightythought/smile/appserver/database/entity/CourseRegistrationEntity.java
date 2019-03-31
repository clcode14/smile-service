package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseRegistrationEntity.java
 * @CreateTime 2019/3/26 22:31
 * @Description: 课程报名实体类
 */
@Entity
@Table(name = "tb_course_registration")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseRegistrationEntity extends BaseEntity {
    /**
     * 课程ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "course_id")
    private int courseId;

    /**
     * 标题
     */
    @Basic
    @Column(name = "title")
    private String title;

    /**
     * 开始时间
     */
    @Basic
    @Column(name = "start_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 价格
     */
    @Basic
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 报名人数
     */
    @Basic
    @Column(name = "members")
    private int members;

    /**
     * 地址
     */
    @Basic
    @Column(name = "address")
    private String address;

    /**
     * 封面图片名称
     */
    @Basic
    @Column(name = "cover_picture_name")
    private String coverPictureName;

    /**
     * 封面图片路径
     */
    @Basic
    @Column(name = "cover_picture_path")
    private String coverPicturePath;

    /**
     * 详情描述
     */
    @Basic
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * 课程图片
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<CourseImageEntity> courseImages;
}
