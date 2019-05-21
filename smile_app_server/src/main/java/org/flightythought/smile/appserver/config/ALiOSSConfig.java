package org.flightythought.smile.appserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ALiOSSConfig.java
 * @CreateTime 2019/5/6 15:15
 * @Description: TODO
 */
@ConfigurationProperties(prefix = "ali-oss")
@Component
@Data
public class ALiOSSConfig {

    private String endpoint;

    private String bucketName;

    private String accessKeyId;
    
    private String accessKeySecret;
}
