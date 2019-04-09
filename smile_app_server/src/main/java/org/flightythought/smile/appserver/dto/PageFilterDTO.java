package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PageFilterDTO
 * @CreateTime 2019/4/9 18:19
 * @Description: TODO
 */
@Data
@ApiModel(value = "分页查询DTO", description = "分页查询DTO")
public class PageFilterDTO {

    @ApiModelProperty(value = "页数，从1开始")
    private Integer pageNumber;

    @ApiModelProperty(value = "每页显示多少数目， 从1开始")
    private Integer pageSize;
}
