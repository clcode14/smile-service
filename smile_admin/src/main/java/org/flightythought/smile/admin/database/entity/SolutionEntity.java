package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionEntity
 * @CreateTime 2019/3/27 1:01
 * @Description: 解决方案实体类
 */
@Entity
@Table(name = "tb_solution")
@Data
@EqualsAndHashCode(callSuper = false)
public class SolutionEntity extends BaseEntity {

    /**
     * 解决方案ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "id", strategy = "identity")
    @Column(name = "id")
    private int id;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;

    /**
     * 解决方案标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 解决方案内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 课程
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_solution_course", joinColumns = {@JoinColumn(name = "solution_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "course_id", nullable = false, updatable = false)})
    private Set<CourseRegistrationEntity> courseRegistrations;

    /**
     * 机构
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_solution_office", joinColumns = {@JoinColumn(name = "solution_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "office_id", nullable = false, updatable = false)})
    private Set<OfficeEntity> offices;

    /**
     * 机构ID
     */
    @Column(name = "agency_id")
    private Integer agencyId;

    /**
     * 康复人数
     */
    @Column(name = "recover_number")
    private Integer recoverNumber;

    /**
     * 阅读数
     */
    @Column(name = "read_num")
    private Integer readNum;

    /**
     * 课程图片
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_solution_image", joinColumns = {@JoinColumn(name = "solution_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<ImagesEntity> images;

}
