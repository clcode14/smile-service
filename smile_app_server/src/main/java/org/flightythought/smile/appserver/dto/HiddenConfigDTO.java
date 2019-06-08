package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/6/3 19:37
 * @Description: TODO
 */
@Data
@ApiModel(value = "隐私配置DTO", description = "隐私配置DTO")
public class HiddenConfigDTO {

    @ApiModelProperty(value = "善行是否公开")
    private Boolean charityHidden;

    @ApiModelProperty(value = "过失是否公开")
    private Boolean faultHidden;

    @ApiModelProperty(value = "动态是否公开")
    private Boolean dynamicHidden;
}
