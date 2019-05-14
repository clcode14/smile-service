package org.flightythought.smile.admin.common;

import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.entity.UserEntity;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
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
    @Value("${oss-status}")
    private Boolean ossStatus;

    @Autowired
    private SysParameterRepository sysParameterRepository;

    private String getStaticUrlByPath(String path, String domainPort) {
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
            if (ossStatus) {
                imageInfo.setUrl(imagesEntity.getOssUrl());
            } else {
                imageInfo.setUrl(getStaticUrlByPath(imagesEntity.getPath(), domainPort));
            }
            imageInfo.setName(imagesEntity.getFileName());
            imageInfo.setId(imagesEntity.getId());
            imageInfo.setSize(imagesEntity.getSize());
            return imageInfo;
        }
        return null;
    }

    public SysUserEntity getCurrentLoginUser() {
        return (SysUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
