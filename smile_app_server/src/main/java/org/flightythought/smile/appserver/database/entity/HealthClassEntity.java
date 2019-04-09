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
 * @ClassName HealthClassEntity
 * @CreateTime 2019/4/9 17:43
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_class")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthClassEntity extends BaseEntity {

    /**
     * 养生大类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "health_id")
    private Integer healthId;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;

    /**
     * 养生大类名称
     */
    @Column(name = "health_name")
    private String healthName;

    /**
     * 背景图片ID
     */
    @Column(name = "bg_image")
    private Integer bgImageId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bg_image", insertable = false, updatable = false)
    private ImagesEntity bgImage;

    /**
     * 内容介紹
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 疾病小类
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "health_id")
    private List<HealthClassDetailEntity> healthClassDetails;
}
