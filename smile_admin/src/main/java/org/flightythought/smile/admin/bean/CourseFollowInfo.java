package org.flightythought.smile.admin.bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 报名课程信息
 * 
 * @author cl47872
 * @version $Id: FollowCourseInfo.java, v 0.1 Jun 8, 2019 9:23:34 AM cl47872 Exp $
 */
@Data
public class CourseFollowInfo {

    /**
     * 报名姓名
     */
    private String        name;

    /**
     * 报名手机号
     */
    private String        phone;

    /**
     * 手机号
     */
    private String        title;

    /**
     * 报名时间
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 价格
     */
    private BigDecimal    price;

}
