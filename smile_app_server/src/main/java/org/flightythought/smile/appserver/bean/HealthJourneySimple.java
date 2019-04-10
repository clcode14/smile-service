package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HealthJourneySimple {

    /**
     * 养生旅程Id
     */
    private Integer journeyId;

    /**
     * 养生旅程名称
     */
    private String journeyName;

    /**
     * 概述
     */
    private String summarize;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 是否已经结束养生旅程
     */
    private Boolean finished;

    /**
     * 阅读数
     */
    private Integer readNum;
}
