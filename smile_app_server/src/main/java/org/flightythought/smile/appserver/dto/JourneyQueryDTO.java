package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "养生旅程查询DTO", description = "养生旅程查询DTO，如果是点击康复案例获取需要传递康复案例ID")
@Data
public class JourneyQueryDTO {
    @ApiModelProperty(value = "养生旅程ID")
    private Integer journeyId;

    @ApiModelProperty(value = "康复案例ID")
    private Integer recoverId;
}
