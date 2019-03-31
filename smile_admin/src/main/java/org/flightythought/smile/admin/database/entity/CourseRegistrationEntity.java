package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;
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
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
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
    @Column(name = "cover_image_id")
    private Integer coverImageId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cover_image_id", insertable = false, updatable = false)
    private Images coverImage;

    /**
     * 详情描述
     */
    @Basic
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * 课程图片
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_course_image", joinColumns = {@JoinColumn(name = "course_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<Images> courseImages;
}
