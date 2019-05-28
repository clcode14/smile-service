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
 * @ClassName DynamicDetailSimple
 * @CreateTime 2019/4/23 22:33
 * @Description: TODO
 */
@Data
public class DynamicDetailSimple {

    /**
     * 发布动态明细ID
     */
    private Integer dynamicDetailId;

    /**
     * 动态ID
     */
    private Integer dynamicId;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 用户
     */
    private UserInfo user;

    /**
     * 转发个数
     */
    private Integer forwardNum;

    /**
     * 评论个数
     */
    private Integer messageNum;

    /**
     * 点赞个数
     */
    private Integer likeNum;

    /**
     * 浏览个数
     */
    private Integer readNum;

    /**
     * 是否公开
     */
    private Boolean hidden;

    /**
     * 动态明细文件
     */
    private List<FileInfo> files;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 自己是否点赞标志位
     */
    private boolean like;
}
