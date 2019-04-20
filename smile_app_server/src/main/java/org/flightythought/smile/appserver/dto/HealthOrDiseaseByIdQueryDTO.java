package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthOrDiseaseByIdQueryDTO
 * @CreateTime 2019/4/13 22:39
 * @Description: TODO
 */
@Data
@ApiModel(value = "养生或疾病小类通过ID查询DTO", description = "根据疾病小类和养生ID查询，可分页，疾病小类ID或养生ID可传可不传")
public class HealthOrDiseaseByIdQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "疾病小类ID集合")
    private List<Integer> diseaseDetailIds;

    @ApiModelProperty(value = "养生ID集合")
    private List<Integer> healthIds;
}
