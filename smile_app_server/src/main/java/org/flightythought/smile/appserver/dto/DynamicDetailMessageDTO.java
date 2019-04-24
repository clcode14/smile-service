package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DynamicDetailMessageDTO
 * @CreateTime 2019/4/24 0:40
 * @Description: TODO
 */
@Data
@ApiModel(value = "动态评论消息DTO", description = "动态评论消息DTO")
public class DynamicDetailMessageDTO {

    @ApiModelProperty(value = "动态明细ID")
    private Integer dynamicDetailId;

    @ApiModelProperty(value = "评论内容", required = true)
    private String message;

    @ApiModelProperty(value = "被回复的消息ID，不是回复则传NULL")
    private Integer parentId;

    @ApiModelProperty(value = "信息发送用户ID")
    private Long fromUserId;

    @ApiModelProperty(value = "信息接收用户ID")
    private Long toUserId;


}
