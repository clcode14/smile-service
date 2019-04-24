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
 * @ClassName DiseaseClassDetailInfo
 * @CreateTime 2019/4/24 23:18
 * @Description: TODO
 */
@Data
public class DiseaseClassDetailInfo {
    /**
     * 疾病小类ID
     */
    private Integer diseaseDetailId;

    /**
     * 疾病大类ID
     */
    private Integer diseaseId;

    /**
     * 编码
     */
    private String number;

    /**
     * 类型
     */
    private String type;

    /**
     * 疾病小类名称
     */
    private String diseaseDetailName;

    /**
     * 疾病大类名称
     */
    private String diseaseName;

    /**
     * 背景图片
     */
    private ImageInfo bgImages;

    /**
     * 疾病小类描述
     */
    private String content;

    /**
     * 疾病小类图标
     */
    private ImageInfo icon;

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
