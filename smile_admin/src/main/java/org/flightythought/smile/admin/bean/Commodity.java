package org.flightythought.smile.admin.bean;

import lombok.Data;
import org.flightythought.smile.admin.database.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 14:56
 * @Description: TODO
 */
@Data
public class Commodity extends BaseEntity {
    /**
     * 商品ID
     */
    private Integer commodityId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品简要介绍
     */
    private String introduce;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 运费类型 0：包邮 1：不包邮
     */
    private Integer freightType;
    
    /**
     * 状态 0：售卖中 1：已停售
     */
    private Integer status;

    /**
     * 商品图片
     */
    private List<ImageInfo> images;

    /**
     * URL
     */
    private String url;

    /**
     * SKU
     */
    private String sku;
}
