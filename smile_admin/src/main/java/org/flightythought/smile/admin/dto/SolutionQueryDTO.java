package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "解决方案查询DTO", description = "解决方案查询DTO")
public class SolutionQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "解决方案名称")
    private String title;

    @ApiModelProperty(value = "编码")
    private String number;

}
