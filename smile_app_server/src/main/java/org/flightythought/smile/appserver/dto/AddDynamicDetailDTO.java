package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "新增动态明细DTO", description = "新增动态明细DTO")
public class AddDynamicDetailDTO {

    @ApiModelProperty(value = "动态ID")
    private Integer dynamicId;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "是否公开")
    private Boolean hidden;

    @ApiModelProperty(value = "文件ID集合")
    private List<Integer> fileIds;
}
