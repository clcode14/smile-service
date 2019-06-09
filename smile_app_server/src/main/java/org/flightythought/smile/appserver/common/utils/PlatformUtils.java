package org.flightythought.smile.appserver.common.utils;

import com.aliyun.oss.OSSClient;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.config.ALiOSSConfig;
import org.flightythought.smile.appserver.database.entity.FilesEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.SysParameterRepository;
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
                URL url = ossClient.generatePresignedUrl(bucketName, filesEntity.getOssKey(), expiration);
                fileInfo.setUrl(url.toString());
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
