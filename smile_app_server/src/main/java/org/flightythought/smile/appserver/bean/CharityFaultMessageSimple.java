package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DynamicDetailMessageSimple
 * @CreateTime 2019/4/24 0:47
 * @Description: TODO
 */
@Data
public class CharityFaultMessageSimple {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 行善过失ID
     */
    private Integer charityFaultId;

    /**
     * 评论内容
     */
    private String message;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 发送者
     */
    private UserInfo fromUser;

    /**
     * 接受者
     */
    private UserInfo toUser;

    /**
     * 接受者是否阅读
     */
    private Boolean read;

    /**
     * 下级信息
     */
    private List<CharityFaultMessageSimple> childMessage;

    /**
     * 点赞个数
     */
    private Integer likeNum;

    /**
     * 当前用户是否点赞
     */
    private Boolean like;

    /**
     * 评论标志类型
     */
    private Integer flagType;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
