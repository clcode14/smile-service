package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "图片DTO", description = "图片DTO")
@Data
public class ImageDTO {
    @ApiModelProperty(value = "图片ID")
    private Integer imageId;
}
