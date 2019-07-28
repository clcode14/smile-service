package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteSimple
 * @CreateTime 2019/4/23 22:33
 * @Description: TODO
 */
@Data
public class JourneyNoteSimple {

    /**
     * 养生旅程ID
     */
    private Integer journeyId;

    /**
     * 日记ID
     */
    private Integer noteId;

    /**
     * 日记内容
     */
    private String content;

    /**
     * 用户
     */
    private UserInfo user;

    /**
     * 评论个数
     */
    private Integer messageNum;

    /**
     * 点赞个数
     */
    private Integer likeNum;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
