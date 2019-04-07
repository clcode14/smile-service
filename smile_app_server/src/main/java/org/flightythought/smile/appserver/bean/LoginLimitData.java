package org.flightythought.smile.appserver.bean;

import lombok.Data;

@Data
public class LoginLimitData {

    private Integer count;

    private Long loginTime;

}
