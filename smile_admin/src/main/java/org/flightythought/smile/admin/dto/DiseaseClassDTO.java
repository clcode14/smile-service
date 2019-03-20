package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 * 疾病大类数据传输对象
 *
 * @author LiLei
 * @date 2019/1/21 15:46
 */
@ApiModel(value = "疾病大类 DTO", description = "疾病大类 DTO")
public class DiseaseClassDTO {
    @ApiModelProperty(value = "疾病大类ID")
    private Integer diseaseId;

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "疾病类目名称")
    private String diseaseName;

    public Integer getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }
}
