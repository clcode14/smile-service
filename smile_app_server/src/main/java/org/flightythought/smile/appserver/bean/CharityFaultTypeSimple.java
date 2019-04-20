package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

@Data
public class CharityFaultTypeSimple {
    /**
     * 行善过失类型ID
     */
    private Integer cfTypeId;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 行善过失类型，0：行善、1：过失
     */
    private Integer type;

    /**
     * 类型积分
     */
    private Integer integral;

    /**
     * 行善过失内容
     */
    private List<CharityFaultTypeContentSimple> contents;

}
