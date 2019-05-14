package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PageFilterDTO.java
 * @CreateTime 2019/5/10 10:43
 * @Description: TODO
 */
@Data
@ApiModel(value = "分页查询DTO", description = "分页查询DTO")
public class PageFilterDTO {
    @ApiModelProperty(value = "每页显示大小")
    private Integer pageSize;

    @ApiModelProperty(value = "页数")
    private Integer pageNumber;
}
