package org.flightythought.smile.appserver.bean;

import lombok.Data;

@Data
public class HealthWaySimple {

    /**
     * 养生方式ID
     */
    private Integer healthWayId;

    /**
     * 养生方式名称
     */
    private String wayName;

    /**
     * 背景图
     */
    private ImageInfo bgImage;

    /**
     * 编码
     */
    private String number;

    /**
     * 介绍描述
     */
    private String content;

    /**
     * 音乐连接
     */
    private String musicUrl;

    /**
     * 养生方式类型
     */
    private Integer type;
}
