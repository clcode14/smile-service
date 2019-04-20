package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HomeBannerEntity
 * @CreateTime 2019/4/14 19:16
 * @Description: TODO
 */
@Entity
@Table(name = "tb_home_banner")
@Data
@EqualsAndHashCode(callSuper = false)
public class HomeBannerEntity extends BaseEntity {
    /**
     * 自增主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 连接
     */
    @Column(name = "link")
    private String link;

    /**
     * 图片ID
     */
    @Column(name = "image_id")
    private Integer imageId;

    /**
     * 图片
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private ImagesEntity image;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
}
