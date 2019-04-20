package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HomeBanner
 * @CreateTime 2019/4/14 19:30
 * @Description: TODO
 */
@Data
public class HomeBanner {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 连接
     */
    private String link;

    /**
     * 图片
     */
    private ImageInfo image;

    /**
     * 排序
     */
    private Integer sort;
}
