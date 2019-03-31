package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ResponseBean.java
 * @CreateTime 2019/3/27 18:51
 * @Description: 响应结果实体类
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseBean {
    private Integer code;
    private String message;
    private Object data;

    private ResponseBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseBean ok(String message, Object data) {
        return new ResponseBean(200, message, data);
    }

    public static ResponseBean ok(String message) {
        return new ResponseBean(200, message);
    }

    public static ResponseBean error(String message, Object data) {
        return new ResponseBean(500, message, data);
    }

    public static ResponseBean error(String message) {
        return new ResponseBean(500, message);
    }

    public static ResponseBean sessionInvalid(String message) {
        return new ResponseBean(50014, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
