package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthNormTypeDTO
 * @CreateTime 2019/4/9 15:55
 * @Description: TODO
 */
@ApiModel(value = "体检指标DTO", description = "体检指标DTO")
@Data
public class HealthNormTypeDTO {

    @ApiModelProperty(value = "体检指标类型ID")
    private Integer normTypeId;

    @ApiModelProperty(value = "体检指标对应数值")
    private String value;
}
