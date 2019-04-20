package org.flightythought.smile.admin.bean;

import lombok.Data;

@Data
public class NormTypeInfo {

    private Integer normTypeId;

    private String normNumber;

    private String normName;

    private String unit;

    private Double max;

    private Double min;

    private Double step;

}
