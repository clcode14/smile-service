package org.flightythought.smile.admin.common;

import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
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
    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Autowired
    private SysParameterRepository sysParameterRepository;

    public String getStaticUrlByPath(String path, String domainPort) {
        String url = domainPort + contentPath + staticUrl + path;
        url = url.replace("\\", "/");
        return url;
    }

    public String getDomainPort() {
        return sysParameterRepository.getDomainPortParam().getParameterValue();
    }

    public ImageInfo getImageInfo(ImagesEntity imagesEntity, String domainPort) {
        if (imagesEntity != null) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setUrl(getStaticUrlByPath(imagesEntity.getPath(), domainPort));
            imageInfo.setName(imagesEntity.getFileName());
            imageInfo.setId(imagesEntity.getId());
            imageInfo.setSize(imagesEntity.getSize());
            return imageInfo;
        }
        return null;
    }

}
