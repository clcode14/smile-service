package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Copyright 2017 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseInfo.java
 * @CreateTime 2019/2/25 22:50
 * @Description: 课程信息对象
 */
public class CourseInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 开始
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startTime;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 当前报名人数
     */
    private Integer member;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }
}
