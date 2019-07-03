package org.flightythought.smile.admin.bean;

import lombok.Data;
import org.flightythought.smile.admin.database.entity.BaseEntity;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWay
 * @CreateTime 2019/5/4 22:30
 * @Description: TODO
 */
@Data
public class HealthWay extends BaseEntity {

    /**
     * 养生方式ID
     */
    private Integer healthWayId;

    /**
     * 养生方式名称
     */
    private String wayName;

    /**
     * 背景图片
     */
    private ImageInfo bgImage;

    /**
     * 编码
     */
    private String number;

    /**
     * 内容
     */
    private String content;

    /**
     * 音乐链接
     */
    private String musicUrl;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 音乐
     */
    private List<FileInfo> music;
}
