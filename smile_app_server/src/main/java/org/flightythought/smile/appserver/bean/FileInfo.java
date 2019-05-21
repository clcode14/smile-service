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
public class FileInfo {
    /**
     * 资源URL
     */
    private String url;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * OSS KEY
     */
    private String ossKey;
}
