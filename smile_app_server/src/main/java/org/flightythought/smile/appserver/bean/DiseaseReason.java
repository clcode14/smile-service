package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReason
 * @CreateTime 2019/4/4 23:25
 * @Description: 疾病原因
 */
@Data
public class DiseaseReason {
    /**
     * 编码
     */
    private String number;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 原因ID
     */
    private Integer reasonId;

    /**
     * 类型ID
     */
    private Integer typeId;

    /**
     * 疾病原因对应的解决方案
     */
    List<SolutionSimple> solutions;


}
