package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CharityFaultQueryDTO
 * @CreateTime 2019/5/26 15:32
 * @Description: TODO
 */
@ApiModel(value = "行善过失查询DTO", description = "行善过失查询DTO")
@Data
public class CharityFaultQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "行善过失类型ID")
    private Integer cfTypeId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
