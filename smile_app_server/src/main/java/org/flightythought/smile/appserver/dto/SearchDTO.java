package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SearchDTO
 * @CreateTime 2019/6/9 22:48
 * @Description: TODO
 */
@ApiModel(value = "搜索DTO", description = "搜索DTO")
@Data
public class SearchDTO extends PageFilterDTO {
    @ApiModelProperty(value = "搜索关键词")
    private String name;
}
