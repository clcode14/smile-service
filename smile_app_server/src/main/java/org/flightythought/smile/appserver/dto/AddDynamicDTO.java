package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "动态DTO", description = "动态DTO，对象存在复用，根据接口需要传递相应的参数")
public class AddDynamicDTO {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "是否隐藏，true隐藏，false公开")
    private Boolean hidden;

    @ApiModelProperty(value = "文件ID集合")
    private List<Integer> fileIds;
}
