package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 账户信息DTO
 * 
 * @author cl47872
 * @version $Id: SysUserDTO.java, v 0.1 Jun 20, 2019 12:40:25 AM cl47872 Exp $
 */
@Data
@ApiModel(value = "账户信息 DTO", description = "账户信息 DTO")
public class SysUserDTO {

    @ApiModelProperty(value = "账户ID，新增时不用传递ID，修改时需传递ID")
    private Integer       id;

    @ApiModelProperty(value = "账户")
    private String        loginName;

    @ApiModelProperty(value = "用户名")
    private String        userName;

    @ApiModelProperty(value = "密码")
    private String        password;

    @ApiModelProperty(value = "角色ID")
    private List<Integer> roleIds;

}
