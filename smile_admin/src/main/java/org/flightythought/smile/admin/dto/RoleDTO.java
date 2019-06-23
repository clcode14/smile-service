package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName RoleDTO
 * @CreateTime 2019/6/20 1:32
 * @Description: TODO
 */
@ApiModel(value = "角色DTO", description = "角色DTO")
@Data
public class RoleDTO {

    @ApiModelProperty(value = "角色ID")
    private Integer id;

    @ApiModelProperty(value = "角色标签名")
    private String role;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "资源集合")
    private List<String> resource;
}
