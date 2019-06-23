package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseResultDTO
 * @CreateTime 2019/6/21 16:14
 * @Description: TODO
 */
@ApiModel(value = "疾病结果DTO", description = "疾病结果DTO")
@Data
public class DiseaseResultDTO {
    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "治疗效果ID")
    private Integer healthResultId;
}
