package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayValueQueryDTO
 * @CreateTime 2019/5/26 14:15
 * @Description: TODO
 */
@Data
@ApiModel(value = "养生方式记录查询DTO", description = "养生方式记录查询DTO")
public class HealthWayValueQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "养生方式ID")
    private Integer healthWayId;
}
