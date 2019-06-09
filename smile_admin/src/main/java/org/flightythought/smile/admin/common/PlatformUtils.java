package org.flightythought.smile.admin.common;

import com.aliyun.oss.OSSClient;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.config.ALiOSSConfig;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.entity.UserEntity;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;

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
    private ALiOSSConfig aLiOSSConfig;

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

    public ImageInfo getImageInfo(ImagesEntity imagesEntity, String domainPort) {
        if (imagesEntity != null) {
            ImageInfo imageInfo = new ImageInfo();
            if (ossStatus) {
                // 获取url
                // 2. 获取OSS参数信息
                String endpoint = aLiOSSConfig.getEndpoint();
                String accessKeyId = aLiOSSConfig.getAccessKeyId();
                String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
                String bucketName = aLiOSSConfig.getBucketName();
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                // 设置URL过期时间为100年
                Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365 * 100);
                // 生成URL
                URL url = ossClient.generatePresignedUrl(bucketName, imagesEntity.getOssKey(), expiration);
                imageInfo.setUrl(url.toString());
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

    public String getOssUrl(String key) {
        String endpoint = aLiOSSConfig.getEndpoint();
        String accessKeyId = aLiOSSConfig.getAccessKeyId();
        String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
        String bucketName = aLiOSSConfig.getBucketName();
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 设置URL过期时间为100年
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365 * 100);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        return url.toString();
    }

    public SysUserEntity getCurrentLoginUser() {
        return (SysUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
