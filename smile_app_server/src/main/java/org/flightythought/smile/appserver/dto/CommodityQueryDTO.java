package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeQueryDTO
 * @CreateTime 2019/5/21 23:53
 * @Description: TODO
 */
@ApiModel(value = "相关商品查询DTO", description = "相关商品查询DTO")
@Data
public class CommodityQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "商品ID")
    private Integer commodityId;

    @ApiModelProperty(value = "解决方案ID")
    private Integer solutionId;
}
