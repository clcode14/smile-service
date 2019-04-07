package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ImageInfo
 * @CreateTime 2019/3/27 23:14
 * @Description: 图片信息
 */
@Data
public class ImageInfo {
    /**
     * 图片展示URL
     */
    private String url;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片主键ID
     */
    private Integer id;

    /**
     * 图片大小
     */
    private Long size;
}
