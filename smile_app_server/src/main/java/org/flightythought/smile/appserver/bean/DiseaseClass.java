package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClass
 * @CreateTime 2019/4/14 23:50
 * @Description: TODO
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
     * 疾病类目名称
     */
    private String diseaseName;

    /**
     * 疾病小类
     */
    private List<DiseaseClassDetailSimple> diseaseClassDetails;
}
