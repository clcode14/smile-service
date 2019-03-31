package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassDTO.java
 * @CreateTime 2019/3/27 16:56
 * @Description: 疾病大类数据传输对象
 */
@ApiModel(value = "疾病大类 DTO", description = "疾病大类 DTO")
@Data
public class DiseaseClassDTO {
    @ApiModelProperty(value = "疾病大类ID")
    private Integer diseaseId;

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "疾病类目名称")
    private String diseaseName;
}
