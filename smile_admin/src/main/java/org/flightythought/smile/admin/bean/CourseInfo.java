package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2017 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseInfo.java
 * @CreateTime 2019/2/25 22:50
 * @Description: 课程信息对象
 */
@Data
public class CourseInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 开始
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime startTime;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 当前报名人数
     */
    private Integer member;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 封面图片
     */
    private ImageInfo coverImage;

    /**
     * 展示图片
     */
    private List<ImageInfo> images;

    /**
     * 课程描述
     */
    private String description;
}
