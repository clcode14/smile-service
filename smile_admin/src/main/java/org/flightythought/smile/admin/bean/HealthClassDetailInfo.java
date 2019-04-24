package org.flightythought.smile.admin.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClassDetailInfo
 * @CreateTime 2019/4/9 18:05
 * @Description: TODO
 */
@Data
public class HealthClassDetailInfo {
    /**
     * 养生小类ID
     */
    private Integer healthDetailId;

    /**
     * 养生大类ID
     */
    private Integer healthId;

    /**
     * 养生小类名称
     */
    private String healthDetailName;

    /**
     * 背景图片
     */
    private ImageInfo bgImage;

    /**
     * 标题
     */
    private String title;

    /**
     * 编码
     */
    private String number;

    /**
     * 内容介绍
     */
    private String content;
}
