package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNote
 * @CreateTime 2019/4/10 23:45
 * @Description: TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyNote {
    /**
     * 养生旅程ID
     */
    private Integer journeyId;

    /**
     * 封面图片URL
     */
    private String coverImageUrl;

    /**
     * 日记内容
     */
    private String content;

    /**
     * 是否发布到朋友圈
     */
    private Boolean circleOfFriends;

    /**
     * 日记时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime noteDate;

    /**
     * 日记时间
     */
    private String noteDateStr;

    /**
     * 图片
     */
    private List<ImageInfo> images;

    /**
     * 体检指标
     */
    private List<JourneyNoteNorm> journeyNoteNorms;


}
