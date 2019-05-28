package org.flightythought.smile.appserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayValueDTO
 * @CreateTime 2019/5/26 14:01
 * @Description: TODO
 */
@ApiModel(value = "养生方式记录DTO", description = "养生方式记录DTO")
@Data
public class HealthWayValueDTO {

    @ApiModelProperty(value = "持续时间（秒）")
    private Integer durationSec;

    @ApiModelProperty(value = "开始时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "养生方式ID")
    private Integer healthWayId;
}
