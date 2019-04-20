package org.flightythought.smile.appserver.common.utils;

import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.SysParameterEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.SysParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public String getImageUrlByPath(String path, String domainPort) {
        String url = domainPort + contentPath + staticUrl + path;
        url = url.replace("\\", "/");
        return url;
    }

    public String getDomainPort() {
        return sysParameterRepository.getDomainPortParam().getParameterValue();
    }

    public UserEntity getCurrentLoginUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getFilePath() {
        return sysParameterRepository.getFilePathParam().getParameterValue();
    }

    public ImageInfo getImageInfo(ImagesEntity imagesEntity) {
        String domainPort = this.getDomainPort();
        if (imagesEntity != null) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setUrl(getImageUrlByPath(imagesEntity.getPath(), domainPort));
            imageInfo.setName(imagesEntity.getFileName());
            imageInfo.setId(imagesEntity.getId());
            imageInfo.setSize(imagesEntity.getSize());
            return imageInfo;
        }
        return null;
    }
}
