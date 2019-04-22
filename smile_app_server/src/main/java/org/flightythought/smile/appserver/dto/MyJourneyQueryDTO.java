package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName MyJourneyQueryDTO
 * @CreateTime 2019/4/21 21:11
 * @Description: TODO
 */
@ApiModel(value = "我的旅程查询DTO", description = "我的旅程查询DTO")
@Data
public class MyJourneyQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "是否结束养生旅程")
    private Boolean finished;
}
