package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ApplyCourseDTO
 * @CreateTime 2019/4/13 18:24
 * @Description: TODO
 */
@ApiModel(value = "预约报名课程DTO", description = "预约报名课程DTO")
@Data
public class ApplyCourseDTO {

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "TOKEN")
    private String token;
}
