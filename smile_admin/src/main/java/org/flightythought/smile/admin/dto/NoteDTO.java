package org.flightythought.smile.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName NoteDTO
 * @CreateTime 2019/6/22 23:18
 * @Description: TODO
 */
@Data
@ApiModel(value = "日记DTO", description = "日记DTO")
public class NoteDTO {

    @ApiModelProperty(value = "日记时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime noteTime;

    @ApiModelProperty(value = "日记内容")
    private String content;

    @ApiModelProperty(value = "配图ID集合")
    private List<Integer> imageIds;

    @ApiModelProperty(value = "封面图ID")
    private Integer coverImageId;
}
