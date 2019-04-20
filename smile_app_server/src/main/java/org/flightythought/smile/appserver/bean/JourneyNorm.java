package org.flightythought.smile.appserver.bean;

import lombok.Data;

@Data
public class JourneyNorm {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 体检指标类型ID
     */
    private Integer normTypeId;

    /**
     * 指标名称
     */
    private String normName;

    /**
     * 养生旅程ID
     */
    private Integer journeyId;

    /**
     * 开始数值1
     */
    private String startValue1;

    /**
     * 开始数值2
     */
    private String startValue2;

    /**
     * 结束数值1
     */
    private String endValue1;

    /**
     * 结束数值2
     */
    private String endValue2;
}
