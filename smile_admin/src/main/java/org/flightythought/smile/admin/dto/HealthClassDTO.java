package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "养生大类 DTO", description = "养生大类 DTO")
public class HealthClassDTO {

    @ApiModelProperty(value = "养生大类ID")
    private Integer healthId;

    @ApiModelProperty(value = "编码")
    private String number;

    @ApiModelProperty(value = "养生大类名称")
    private String healthName;

    @ApiModelProperty(value = "背景图片ID")
    private Integer bgImageId;

    @ApiModelProperty(value = "内容介紹")
    private String content;
}
