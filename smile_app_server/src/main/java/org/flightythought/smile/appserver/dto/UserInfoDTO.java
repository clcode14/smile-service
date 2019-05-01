package org.flightythought.smile.appserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/4/30 17:30
 * @Description: TODO
 */
@ApiModel(value = "用户信息DTO", description = "用户信息DTO")
@Data
public class UserInfoDTO {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "头像ID")
    private Integer photoId;

    @ApiModelProperty(value = "生日")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty(value = "身高")
    private Double height;

    @ApiModelProperty(value = "体重")
    private Double weight;
}
