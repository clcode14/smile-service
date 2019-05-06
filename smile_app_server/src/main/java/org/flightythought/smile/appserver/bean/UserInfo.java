package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserInfo
 * @CreateTime 2019/4/16 22:10
 * @Description: 用户信息对象
 */
@Data
public class UserInfo {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 头像
     */
    private String photo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 登陆次数
     */
    private Integer loginCount;

    /**
     * 身高
     */
    private Double height;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 生日
     */
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 性别
     */
    private Integer sex;
}
