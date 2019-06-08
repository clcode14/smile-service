package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "课程报名查询DTO", description = "课程报名查询DTO")
public class CourseFollowQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

}
