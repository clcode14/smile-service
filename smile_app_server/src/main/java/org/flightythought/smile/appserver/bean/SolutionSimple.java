package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionSimple
 * @CreateTime 2019/4/4 23:28
 * @Description: 解决方案简化对象
 */
@Data
public class SolutionSimple {
    /**
     * 解决方案对应图片URL
     */
    private List<String> imageUrls;

    /**
     * 解决方案标题
     */
    private String title;

    /**
     * 康复人数
     */
    private Integer recoverNumber;

    /**
     * 解决方案ID
     */
    private Integer solutionId;
}
