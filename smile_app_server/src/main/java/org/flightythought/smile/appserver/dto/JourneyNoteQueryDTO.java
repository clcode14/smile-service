package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteQueryDTO
 * @CreateTime 2019/4/10 23:43
 * @Description: TODO
 */
@Data
@ApiModel(value = "养生旅程日记查询DTO", description = "养生旅程日记查询DTO")
public class JourneyNoteQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "养生旅程ID")
    private Integer journeyId;
}
