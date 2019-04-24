package org.flightythought.smile.appserver.bean;

import lombok.Data;

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
}
