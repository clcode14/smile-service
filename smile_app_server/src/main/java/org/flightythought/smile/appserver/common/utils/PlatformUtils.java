package org.flightythought.smile.appserver.common.utils;

import org.flightythought.smile.appserver.database.entity.SysParameterEntity;
import org.flightythought.smile.appserver.database.repository.SysParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PlatformUtils
 * @CreateTime 2019/4/4 23:58
 * @Description: 平台工具类
 */
@Component
public class PlatformUtils {
    @Value("${image-url}")
    private String imageRequest;
    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Autowired
    private SysParameterRepository sysParameterRepository;

    public String getImageUrlByPath(String path, String domainPort) {
        String url = domainPort + contentPath + imageRequest + path;
        url = url.replace("\\", "/");
        return url;
    }

    public String getDomainPort() {
        return sysParameterRepository.getDomainPortParam().getParameterValue();
    }
}
