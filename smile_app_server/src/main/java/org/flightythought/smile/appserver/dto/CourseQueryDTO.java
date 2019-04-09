package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "相关课程查询DTO", description = "相关课程查询DTO")
public class CourseQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "解决方案ID")
    private Integer solutionId;

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;
}
