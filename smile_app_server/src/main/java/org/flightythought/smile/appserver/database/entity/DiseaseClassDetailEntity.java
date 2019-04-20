package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassDetailEntity.java
 * @CreateTime 2019/3/27 17:14
 * @Description: 疾病小类实体类
 */
@Entity
@Table(name = "tb_disease_class_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class DiseaseClassDetailEntity extends BaseEntity {
    /**
     * 疾病小类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "disease_detail_id")
    private Integer diseaseDetailId;

    /**
     * 疾病大类ID
     */
    @Column(name = "disease_id")
    private Integer diseaseId;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;

    /**
     * 疾病小类类型
     * 0：普通疾病
     * 1：常见疾病
     */
    @Basic
    @Column(name = "type")
    private String type;

    /**
     * 疾病小类名称
     */
    @Column(name = "disease_detail_name")
    private String diseaseDetailName;

    /**
     * 背景图
     */
    @Column(name = "bg_images")
    private Integer bgImagesId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bg_images", updatable = false, insertable = false)
    private ImagesEntity bgImage;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 图标
     */
    @Column(name = "icon")
    private Integer iconId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon", updatable = false, insertable = false)
    private ImagesEntity icon;
}
