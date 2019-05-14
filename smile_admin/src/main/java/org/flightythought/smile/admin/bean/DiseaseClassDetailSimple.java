package org.flightythought.smile.admin.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassDetailSimple
 * @CreateTime 2019/4/6 12:57
 * @Description: TODO
 */
@Data
public class DiseaseClassDetailSimple {

    /**
     * 疾病小类ID
     */
    private Integer diseaseDetailId;

    /**
     * 疾病大类ID
     */
    private Integer diseaseId;

    /**
     * 疾病大类名称
     */
    private String diseaseClassName;

    /**
     * 疾病小类名称
     */
    private String diseaseClassDetailName;

    /**
     * 背景图片URL
     */
    private String bgImageUrl;

    /**
     * 内容介绍
     */
    private String content;

    /**
     * 图标URL
     */
    private String iconUrl;
}
