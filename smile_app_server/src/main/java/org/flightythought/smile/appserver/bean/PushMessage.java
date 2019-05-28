package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PushMessage
 * @CreateTime 2019/5/27 17:42
 * @Description: TODO
 */
@Data
public class PushMessage<T> {
    /**
     * 推送信息类型 0：系统通知，1：点赞通知，2.新增粉丝通知，3.动态评论通知，4.爱心养生评论通知
     */
    private int type;

    /**
     * CODE
     */
    private String code;

    /**
     * 推送数据
     */
    private T data;

    /**
     * 信息标题
     */
    private String title;

    /**
     * 信息
     */
    private String message;

    /**
     * 推送数据ID
     */
    private Integer pushDataId;
}
