package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

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
     * 养生ID
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

    /**
     * 养生对应的解决方案
     */
    private List<SolutionSimple> solutions;
}
