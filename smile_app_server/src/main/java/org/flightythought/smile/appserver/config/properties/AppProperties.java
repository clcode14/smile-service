package org.flightythought.smile.appserver.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName AppProperties.java
 * @CreateTime 2019/3/29 0:29
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "app-config")
public class AppProperties {
    private String tokenKey;
    private String codeKey;
    private Integer tokenExpirationTime;
}

