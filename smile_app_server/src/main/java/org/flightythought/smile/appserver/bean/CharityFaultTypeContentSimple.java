package org.flightythought.smile.appserver.bean;

import lombok.Data;

@Data
public class CharityFaultTypeContentSimple {

    /**
     * 内容ID
     */
    private Integer contentId;

    /**
     * 行善过失ID
     */
    private Integer cfTypeId;

    /**
     * 内容
     */
    private String content;
}
