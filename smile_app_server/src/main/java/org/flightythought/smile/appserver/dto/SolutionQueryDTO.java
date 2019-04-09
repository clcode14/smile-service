package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "解决方案查询DTO", description = "解决方案查询DTO")
public class SolutionQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "疾病愿意ID")
    private Integer diseaseReasonId;
}
