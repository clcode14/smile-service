package org.flightythought.smile.appserver.common;

import lombok.Getter;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PushCodeEnum
 * @CreateTime 2019/5/27 18:20
 * @Description: TODO
 */
@Getter
public enum PushCodeEnum {
    /**
     * 系统信息推送
     */
    SYSTEM("system"),

    /**
     * 动态点赞
     */
    DYNAMIC_LIKE("dynamic_like"),

    /**
     * 动态评论
     */
    DYNAMIC_MESSAGE("dynamic_message"),

    /**
     * 动态评论点赞
     */
    DYNAMIC_MESSAGE_LIKE("dynamic_message_like"),

    /**
     * 爱心养生评论
     */
    CHARITY_FAULT_MESSAGE("charity_fault_message"),

    /**
     * 爱心养生评论点赞
     */
    CHARITY_FAULT_MESSAGE_LIKE("charity_fault_message_like"),

    /**
     * 粉丝通知
     */
    ADD_FAN("add_fan");

    private String message;

    PushCodeEnum(String message) {
        this.message = message;
    }
}
