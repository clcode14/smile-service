package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthNormDTO
 * @CreateTime 2019/6/21 16:56
 * @Description: TODO
 */
@Data
@ApiModel(value = "体检指标DTO", description = "体检指标DTO")
public class HealthNormDTO {

    @ApiModelProperty(value = "体检指标类型ID")
    private Integer normTypeId;

    @ApiModelProperty(value = "开始数值1")
    private String startValue1;

    @ApiModelProperty(value = "开始数值2")
    private String startValue2;

    @ApiModelProperty(value = "结束数值1")
    private String endValue1;

    @ApiModelProperty(value = "结束数值2")
    private String endValue2;

}
