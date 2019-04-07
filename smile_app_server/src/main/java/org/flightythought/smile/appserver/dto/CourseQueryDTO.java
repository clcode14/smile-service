package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "相关课程查询DTO", description = "相关课程查询DTO")
@Data
public class CourseQueryDTO {
    @ApiModelProperty(value = "解决方案ID")
    private Integer solutionId;

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
}
