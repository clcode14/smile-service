package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "养生方式查询DTO", description = "可以根据养生方式ID，分页查询获取养生方式")
@Data
public class HealthWayQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "养生方式ID集合")
    private List<Integer> healthWayIds;
}
