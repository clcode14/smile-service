package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DynamicDetailMessageQueryDTO
 * @CreateTime 2019/5/26 18:32
 * @Description: TODO
 */
@ApiModel(value = "动态明细评论查询DTO", description = "动态明细评论查询DTO")
@Data
public class DynamicDetailMessageQueryDTO extends PageFilterDTO{

    @ApiModelProperty(value = "动态明细ID")
    private Integer dynamicDetailId;

    @ApiModelProperty(value = "评论ID")
    private Integer messageId;
}
