package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthEntity
 * @CreateTime 2019/4/9 17:43
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_way")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthWayEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "health_way_id")
    private Integer healthWayId;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;
    /**
     * 名称
     */
    @Column(name = "way_name")
    private String wayName;

    @Column(name = "bg_image")
    private Integer bgImage;

    /**
     * 背景图片
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bg_image", insertable = false, updatable = false)
    private ImagesEntity images;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    @Column(name = "music_url")
    private String musicUrl;

}
