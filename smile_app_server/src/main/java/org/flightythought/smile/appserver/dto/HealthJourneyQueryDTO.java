package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "养生旅程查询DTO", description = "养生旅程查询DTO")
@Data
public class HealthJourneyQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "用户ID")
    private Long userId;
}
