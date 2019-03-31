package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SmsCodeData
 * @CreateTime 2019/3/29 0:08
 * @Description: 短信验证码数据
 */
@Data
public class SmsCodeData {
    /**
     * 手机号码
     */
    private String phone;

    /**
     * 短信验证码
     */
    private String vCode;

    /**
     * 申请验证码时间戳
     */
    private Long time;
}
