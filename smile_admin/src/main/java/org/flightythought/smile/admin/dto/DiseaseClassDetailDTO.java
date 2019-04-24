package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassDetailDTO.java
 * @CreateTime 2019/3/27 16:54
 * @Description: 疾病小类数据传输对象
 */
@ApiModel(value = "疾病小类 DTO", description = "疾病小类 DTO")
@Data
public class DiseaseClassDetailDTO {

    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "疾病类目ID")
    private Integer diseaseId;

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "疾病小类名称")
    private String diseaseDetailName;

    @ApiModelProperty(value = "疾病小类类型 0：普通疾病1，常见疾病")
    private String type;

    @ApiModelProperty(value = "疾病小类背景图ID")
    private ImageDTO bgImages;

    @ApiModelProperty(value = "疾病小类描述")
    private String content;

    @ApiModelProperty(value = "疾病小类图标ID")
    private ImageDTO icon;
}
