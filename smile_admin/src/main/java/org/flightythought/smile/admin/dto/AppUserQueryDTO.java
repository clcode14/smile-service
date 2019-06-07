package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户查询DTO", description = "用户查询DTO")
public class AppUserQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "起始注册日期,时间格式yyyy-MM-dd")
    private String createTimeStart;

    @ApiModelProperty(value = "截止注册日期,时间格式yyyy-MM-dd")
    private String createTimeEnd;
}
