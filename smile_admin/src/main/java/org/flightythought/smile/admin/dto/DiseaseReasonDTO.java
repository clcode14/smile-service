package org.flightythought.smile.admin.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonDTO
 * @CreateTime 2019/4/2 0:25
 * @Description: TODO
 */
@ApiModel(value = "疾病原因DTO", description = "疾病原因DTO")
@Data
public class DiseaseReasonDTO {

    @ApiModelProperty(value = "疾病原因ID")
    private Integer id;

    @ApiModelProperty(value = "疾病大类ID")
    private Integer diseaseId;

    @ApiModelProperty(value = "疾病小类ID")
    private Integer diseaseDetailId;

    @ApiModelProperty(value = "原因编码")
    private String number;

    @ApiModelProperty(value = "原因标题")
    private String title;

    @ApiModelProperty(value = "原因描述")
    private String content;

    @ApiModelProperty(value = "解决方案Id集合")
    private List<Integer> solutionIds;

    @ApiModelProperty(value = "类型")
    private Integer type;
}
