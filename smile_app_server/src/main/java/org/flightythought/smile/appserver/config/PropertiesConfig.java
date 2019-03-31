package org.flightythought.smile.appserver.config;

import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PropertiesConfig.java
 * @CreateTime 2019/3/29 0:33
 * @Description:
 */
@EnableConfigurationProperties({AppProperties.class})
@Configuration
public class PropertiesConfig {

}
