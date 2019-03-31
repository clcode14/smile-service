package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionDTO
 * @CreateTime 2019/3/27 18:44
 * @Description: 解决方案数据传输对象
 */
@Data
@ApiModel(value = "解决方案 DTO", description = "解决方案 DTO")
public class SolutionDTO {

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "解决方案名称(标题)")
    private String title;

    @ApiModelProperty(value = "解决方案内容")
    private String content;

    @ApiModelProperty(value = "课程ID(相关课程)")
    private Integer courseId;

}
