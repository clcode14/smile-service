package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

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

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
