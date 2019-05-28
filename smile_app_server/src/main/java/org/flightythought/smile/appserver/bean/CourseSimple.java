package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.flightythought.smile.appserver.database.entity.UserFollowCourseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseSimple {

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 可报名人数
     */
    private Integer members;

    /**
     * 报名人数
     */
    private Integer applyCount;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 封面图片
     */
    private String coverImageUrl;

    /**
     * 课程图片
     */
    private List<String> courseImages;

    /**
     * 详情描述
     */
    private String description;

    /**
     * 课程类型ID
     */
    private Integer typeId;

    /**
     * 报名者信息
     */
    private UserFollowCourseEntity userFollowCourse;

}
