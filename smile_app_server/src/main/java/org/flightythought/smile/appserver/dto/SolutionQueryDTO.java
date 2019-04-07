package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "解决方案查询DTO", description = "解决方案查询DTO")
@Data
public class SolutionQueryDTO {
    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "疾病愿意ID")
    private Integer diseaseReasonId;

    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
}
