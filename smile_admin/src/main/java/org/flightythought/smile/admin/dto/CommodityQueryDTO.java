package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 15:34
 * @Description: TODO
 */
@Data
@ApiModel(value = "相关商品查询DTO", description = "相关商品查询DTO")
public class CommodityQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "商品SKU")
    private String sku;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "价格最大值")
    private BigDecimal priceMax;

    @ApiModelProperty(value = "价格最小值")
    private BigDecimal priceMin;
}
