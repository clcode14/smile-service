package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecoverCaseSimple {

    /**
     * 主键ID
     */
    private Integer recoverId;

    /**
     * 养生旅程ID
     */
    private Integer journeyId;

    /**
     * 解决方案ID
     */
    private Integer solutionId;

    /**
     * 康复案例标题
     */
    private String title;

    /**
     * 案例开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime caseStartTime;

    /**
     * 案例结束时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime caseEndTime;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 阅读数
     */
    private Long readNum;

    /**
     * 封面图
     */
    private ImageInfo coverImage;
}
