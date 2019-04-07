package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "有关疾病明细查询DTO", description = "有关疾病明细查询DTO")
@Data
public class AboutDiseaseDetailQueryDTO {
    @ApiModelProperty(value = "页码")
    private Integer pageNumber;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "类型，疾病原因中获取中医和西医")
    private String type;
}
