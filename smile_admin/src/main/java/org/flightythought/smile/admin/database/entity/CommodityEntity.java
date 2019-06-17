package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:12
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_commodity")
@EqualsAndHashCode(callSuper = false)
public class CommodityEntity extends BaseEntity {

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 商品SKU
     */
    @Column(name = "sku")
    private String sku;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 商品简要介绍
     */
    @Column(name = "introduce", columnDefinition = "text")
    private String introduce;

    /**
     * 商品详情
     */
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * 商品价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 运费
     */
    @Column(name = "freight")
    private BigDecimal freight;

    /**
     * 运费类型
     */
    @Column(name = "freight_type")
    private Integer freightType;
    
    /**
     * 商品URL
     */
    @Column(name = "url")
    private String url;
    
    /**
     * 状态
     */
    @Column(name = "status")
    private Integer status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_commodity_image", joinColumns = {@JoinColumn(name = "commodity_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<ImagesEntity> images;
}
