package org.flightythought.smile.admin.bean;

import lombok.Data;

@Data
public class JourneyNormInfo {

    private Integer id;

    private NormTypeInfo normTypeInfo;

    private String startValue1;

    private String startValue2;

    private String endValue1;

    private String endValue2;
}
