package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户关注疾病DTO", description = "用户关注疾病DTO")
@Data
public class UserFollowDiseaseDTO {
    @ApiModelProperty(value = "疾病小类ID", required = true)
    private Integer diseaseDetailId;
}
