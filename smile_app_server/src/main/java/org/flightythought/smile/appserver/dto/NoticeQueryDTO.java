package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName NoticeQueryDTO
 * @CreateTime 2019/5/28 20:28
 * @Description: TODO
 */
@ApiModel(value = "消息推送查询DTO", description = "消息推送查询DTO")
@Data
public class NoticeQueryDTO extends PageFilterDTO {
    @ApiModelProperty(value = "推送信息类型 0：系统通知，1：点赞通知，2.新增粉丝通知，3.评论通知")
    private Integer type;

    @ApiModelProperty(value = "查询已读或者未读，如果不传递则获取全部消息")
    private Boolean read;
}
