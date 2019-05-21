package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseAndHealthResult
 * @CreateTime 2019/4/13 22:50
 * @Description: TODO
 */
@ApiModel(value = "养生小类对应的养生成果DTO", description = "养生方式对应的养生成果DTO")
@Data
public class DiseaseAndHealthResult {
    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "养生成果ID")
    private Integer healthResultId;
}
