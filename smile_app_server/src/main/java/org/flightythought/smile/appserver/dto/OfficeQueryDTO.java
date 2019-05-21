package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeQueryDTO
 * @CreateTime 2019/5/21 23:53
 * @Description: TODO
 */
@ApiModel(value = "相关机构查询DTO", description = "相关机构查询DTO")
@Data
public class OfficeQueryDTO extends PageFilterDTO{

    @ApiModelProperty(value = "机构ID")
    private Long officeId;

    @ApiModelProperty(value = "解决方案ID")
    private Integer solutionId;
}
