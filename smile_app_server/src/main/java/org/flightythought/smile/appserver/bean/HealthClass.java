package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClass
 * @CreateTime 2019/4/9 18:03
 * @Description: TODO
 */
@Data
public class HealthClass {

    /**
     * 养生大类ID
     */
    private Integer healthId;

    /**
     * 编码
     */
    private String number;

    /**
     * 养生大类名称
     */
    private String healthName;

    /**
     * 背景图片
     */
    private ImageInfo bgImage;

    /**
     * 内容介绍
     */
    private String content;
}
