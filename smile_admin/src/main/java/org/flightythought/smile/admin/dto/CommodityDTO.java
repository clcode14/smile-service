package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 14:58
 * @Description: TODO
 */
@ApiModel(value = "商品DTO", description = "商品DTO")
@Data
public class CommodityDTO {

    @ApiModelProperty(value = "商品ID")
    private Integer commodityId;

    @ApiModelProperty(value = "商品SKU")
    private String sku;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品简介")
    private String introduce;

    @ApiModelProperty(value = "商品详情")
    private String description;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "运费")
    private BigDecimal freight;

    @ApiModelProperty(value = "运费类型 0:包邮 1:不包邮")
    private Integer freightType;
    
    @ApiModelProperty(value = "商品url")
    private String url;
    
    @ApiModelProperty(value = "售卖状态")
    private String status;

    @ApiModelProperty(value = "图片ID")
    private List<Integer> imageIds;
}
