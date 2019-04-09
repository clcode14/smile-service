package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "有关疾病明细查询DTO", description = "有关疾病明细查询DTO")
public class AboutDiseaseDetailQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "类型，疾病原因中获取中医和西医")
    private Integer type;
}
