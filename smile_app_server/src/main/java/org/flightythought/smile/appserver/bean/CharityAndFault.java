package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CharityAndFault {

    /**
     * 行善
     */
    private List<CharityFaultTypeSimple> charity = new ArrayList<>();

    /**
     * 过失
     */
    private List<CharityFaultTypeSimple> fault = new ArrayList<>();
}
