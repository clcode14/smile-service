package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 18:01
 * @Description: TODO
 */
@Data
@ApiModel(value = "审核旅程DTO", description = "审核旅程DTO")
public class CheckCaseAuditDTO {
    @ApiModelProperty(value = "案例标题")
    private String title;

    @ApiModelProperty(value = "审核类型，1：标记为已审核，2：审核被被评为案例")
    private Integer checkType;
}
