package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:56
 * @Description: TODO
 */
@Data
public class CommoditySimple {

    /**
     * 商品ID
     */
    private Integer commodityId;

    /**
     * 商品SKU
     */
    private String sku;

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
     * 商品图片
     */
    private List<ImageInfo> images;

    /**
     * 商品URL
     */
    private String url;
}
