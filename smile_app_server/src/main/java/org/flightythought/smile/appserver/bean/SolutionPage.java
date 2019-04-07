package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

/**
 * 解决方案页面对象
 */
@Data
public class SolutionPage {
    /**
     * 配图
     */
    private List<String> imageUrls;

    /**
     * 是否收藏
     */
    private boolean collection;

    /**
     * 解决方案标题
     */
    private String title;

    /**
     * 解决方案内容
     */
    private String content;
}
