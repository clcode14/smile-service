package org.flightythought.smile.appserver.bean;

import lombok.Data;

@Data
public class DynamicSimple {

    /**
     * 动态ID
     */
    private Integer dynamicId;

    /**
     * 动态标题
     */
    private String title;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 用户
     */
    private UserInfo user;

    /**
     * 动态个数
     */
    private Integer dynamicDetailNum;

    /**
     * 浏览数
     */
    private Integer readNum;

    /**
     * 是否隐藏
     */
    private Boolean hidden;
}
