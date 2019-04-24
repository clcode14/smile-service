package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseRegistrationDTO.java
 * @CreateTime 2019/3/27 17:07
 * @Description: 课程报名数据传输对象
 */
@ApiModel(value = "课程报名 DTO", description = "课程报名 DTO")
@Data
public class CourseRegistrationDTO {

    @ApiModelProperty(value = "课程ID, 新增课程不用传，修改课程需传入课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "标题", required = true)
    private String title;

    @ApiModelProperty(value = "开始时间", required = true)
    private String startTime;

    @ApiModelProperty(value = "报名人数")
    private Integer members;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "活动地址")
    private String address;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "封面图片")
    private ImageDTO coverImage;

    @ApiModelProperty(value = "展示图片")
    private List<ImageDTO> courseImages;

    @ApiModelProperty(value = "课程类型ID")
    private Integer typeId;
}
