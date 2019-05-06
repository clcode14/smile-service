package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayDTO
 * @CreateTime 2019/5/4 22:11
 * @Description: TODO
 */
@Data
@ApiModel(value = "养生方式DTO", description = "养生方式DTO")
public class HealthWayDTO {
    @ApiModelProperty(value = "养生方式ID")
    private Integer healthWayId;

    @ApiModelProperty(value = "养生方式名称")
    private String wayName;

    @ApiModelProperty(value = "背景图片ID")
    private Integer bgImageId;

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "养生方式内容描述")
    private String content;

    @ApiModelProperty(value = "音乐链接")
    private String musicUrl;

    @ApiModelProperty(value = "类型，参数值待定")
    private Integer type;
}
