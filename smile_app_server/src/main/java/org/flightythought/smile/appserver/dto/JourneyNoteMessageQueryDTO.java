package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteMessageQueryDTO
 * @CreateTime 2019/5/26 18:32
 * @Description: TODO
 */
@ApiModel(value = "养生日记评论查询DTO", description = "养生日记评论查询DTO")
@Data
public class JourneyNoteMessageQueryDTO extends PageFilterDTO{

    @ApiModelProperty(value = "养生日记ID")
    private Integer journeyNoteId;

    @ApiModelProperty(value = "评论ID")
    private Integer messageId;
}
