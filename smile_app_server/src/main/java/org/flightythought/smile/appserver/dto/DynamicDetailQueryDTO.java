package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DynamicDetailQueryDTO.java
 * @CreateTime 2019/5/1 12:56
 * @Description: TODO
 */
@Data
@ApiModel(value = "动态明细查询DTO", description = "动态明细查询DTO")
public class DynamicDetailQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "动态ID", required = true)
    private Integer dynamicId;
}
