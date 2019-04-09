package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "文件图片DTO", description = "文件图片DTO")
@Data
public class FileImageDTO {
    @ApiModelProperty(value = "文件报告ID或图片ID")
    private Integer id;
}
