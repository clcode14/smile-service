package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "康复案例DTO", description = "康复案例DTO")
@Data
public class RecoverCaseDTO {
    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;
}
