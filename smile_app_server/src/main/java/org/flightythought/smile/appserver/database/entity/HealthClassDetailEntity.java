package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClassDetailEntity
 * @CreateTime 2019/4/9 17:45
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_class_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthClassDetailEntity extends BaseEntity {

    /**
     * 养生小类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "health_detail_id")
    private Integer healthDetailId;

    /**
     * 所属养生大类ID
     */
    @Column(name = "health_id")
    private Integer healthId;

    /**
     * 养生小类名称
     */
    @Column(name = "health_detail_name")
    private String healthDetailName;

    /**
     * 背景图
     */
    @Column(name = "bg_image")
    private Integer bgImageId;

    /**
     * 背景图片
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bg_image", insertable = false, updatable = false)
    private ImagesEntity bgImage;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 解决方案
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_health_class_detail_to_solution", joinColumns = {@JoinColumn(name = "health_detail_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "solution_id", nullable = false, updatable = false)})
    private List<SolutionEntity> solutions;
}
