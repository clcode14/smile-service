package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/10 15:30
 * @Description: TODO
 */
@Data
@ApiModel(value = "疾病大类查询DTO", description = "疾病大类查询DTO")
public class DiseaseQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "疾病名称")
    private String diseaseName;
}
