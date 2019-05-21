package org.flightythought.smile.appserver.common.utils;

import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.database.entity.FilesEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
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
    @Value("${oss-status}")
    private Boolean ossStatus;

    @Autowired
    private SysParameterRepository sysParameterRepository;

    private String getStaticUrlByPath(String path, String domainPort) {
        String url = domainPort + contentPath + staticUrl + path;
        url = url.replace("\\", "/");
        return url;
    }

    public String getMedicalReportStaticUrlByPath(String path, String domainPort) {
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

    public FileInfo getFileInfo(FilesEntity filesEntity, String domainPort) {
        if (filesEntity != null) {
            FileInfo fileInfo = new FileInfo();
            // 文件ID
            fileInfo.setId(filesEntity.getId());
            // 文件名称
            fileInfo.setName(filesEntity.getFileName());
            // 资源URL
            if (!ossStatus) {
                fileInfo.setUrl(getStaticUrlByPath(filesEntity.getPath(), domainPort));
            } else {
                fileInfo.setUrl(filesEntity.getOssUrl());
            }
            // 文件大小
            fileInfo.setSize(filesEntity.getSize());
            // 文件类型
            fileInfo.setFileType(filesEntity.getFileType());
            // OSS KEY
            fileInfo.setOssKey(filesEntity.getOssKey());
            return fileInfo;
        }
        return null;
    }
}
