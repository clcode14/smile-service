package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "疾病小类 DTO", description = "疾病小类 DTO")
public class DiseaseClassDetailDTO {

    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "疾病类目ID")
    private Integer diseaseId;

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "疾病小类名称")
    private String diseaseDetailName;

    public Integer getDiseaseDetailId() {
        return diseaseDetailId;
    }

    public void setDiseaseDetailId(Integer diseaseDetailId) {
        this.diseaseDetailId = diseaseDetailId;
    }

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

    public String getDiseaseDetailName() {
        return diseaseDetailName;
    }

    public void setDiseaseDetailName(String diseaseDetailName) {
        this.diseaseDetailName = diseaseDetailName;
    }
}
