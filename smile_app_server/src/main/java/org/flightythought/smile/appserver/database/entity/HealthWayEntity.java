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
 * @ClassName HealthWayEntity
 * @CreateTime 2019/4/9 17:45
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_way")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthWayEntity extends BaseEntity {

    /**
     * 养生小类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "health_way_id")
    private Integer healthWayId;

    /**
     * 养生方式名称
     */
    @Column(name = "way_name")
    private String wayName;

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
     * 音乐链接
     */
    @Column(name = "music_url")
    private String musicUrl;

    /**
     * 养生方式类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 音乐链接
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_way_id", insertable = false, updatable = false)
    private List<HealthWayMusicEntity> musicEntities;
}
