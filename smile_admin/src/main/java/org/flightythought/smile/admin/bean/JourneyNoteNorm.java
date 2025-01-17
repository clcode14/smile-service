package org.flightythought.smile.admin.bean;

import lombok.Data;

@Data
public class JourneyNoteNorm {
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
     * 数值1
     */
    private String value1;

    /**
     * 数值2
     */
    private String value2;

}
