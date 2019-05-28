package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClass
 * @CreateTime 2019/5/2 20:52
 * @Description: 疾病大类
 */
@Data
public class DiseaseClass {

    /**
     * 疾病大类ID
     */
    private Integer diseaseId;

    /**
     * 编码
     */
    private String number;

    /**
     * 疾病大类名称
     */
    private String diseaseName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建者
     */
    private String createUserName;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改者
     */
    private String updateUserName;

    /**
     * 修改时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
