package org.flightythought.smile.admin.service.impl;

import com.aliyun.oss.OSSClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.config.ALiOSSConfig;
import org.flightythought.smile.admin.database.entity.AppVersionEntity;
import org.flightythought.smile.admin.database.entity.SysParameterEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.AppVersionRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SystemServiceImpl
 * @CreateTime 2019/5/27 21:14
 * @Description: TODO
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private ALiOSSConfig aLiOSSConfig;
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private AppVersionRepository appVersionRepository;

    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;
    @Value("${oss-status}")
    private Boolean ossStatus;

    private static final Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Override
    public AppVersionEntity uploadAppFile(MultipartFile multipartFile, Integer versionId, String version, Boolean forceUpdate, String description) {
        String fileType = "app_file";
        // 获取当前登陆用户
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        String filePath = "", userPath;
        if (ossStatus) {
            if (!"".equals(fileType)) {
                userPath = fileType + "/" + userEntity.getId();
            } else {
                userPath = userEntity.getId() + "";
            }
        } else {
            if (!"".equals(fileType)) {
                userPath = File.separator + fileType + File.separator + userEntity.getId();
            } else {
                userPath = File.separator + userEntity.getId();
            }
        }
        if (!ossStatus) {
            // 获取系统参数
            SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
            if (sysParameterEntity == null) {
                throw new FlightyThoughtException("请设置上传文件路径系统参数");
            } else {
                filePath = sysParameterEntity.getParameterValue();
                File file = new File(filePath + userPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        }
        AppVersionEntity appVersionEntity = new AppVersionEntity();
        if (multipartFile != null) {
            // 上传封面图片到服务器
            // 获取原始图片名称
            String filename = multipartFile.getOriginalFilename();
            // 新建文件
            if (filename != null) {
                if (!ossStatus) {
                    String path = userPath + File.separator + System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                    // 文件路径
                    appVersionEntity.setPath(path);
                    File coverPictureFile = new File(filePath + path);
                    try {
                        // 创建文件输出流
                        FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                        // 复制文件
                        IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {

                        throw new FlightyThoughtException("上传文件失败", e);
                    }
                } else {
                    // OSS 对象存储
                    // 1. 获取文件后缀名
                    String suffix = getSuffix(multipartFile);
                    // 2. 获取OSS参数信息
                    String endpoint = aLiOSSConfig.getEndpoint();
                    String accessKeyId = aLiOSSConfig.getAccessKeyId();
                    String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
                    String bucketName = aLiOSSConfig.getBucketName();
                    // 创建OSSClient实例
                    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                    // 文件KEY
                    String fileKey = userPath + "/" + System.currentTimeMillis() + "." + suffix;
                    // 上传文件
                    try {
                        ossClient.putObject(bucketName, fileKey, multipartFile.getInputStream());
                        // 设置URL过期时间为100年
                        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365 * 100);

                        // 生成URL
                        URL url = ossClient.generatePresignedUrl(bucketName, fileKey, expiration);
                        // 保存数据对象
                        appVersionEntity.setKey(fileKey);
                        appVersionEntity.setUrl(url.toString());
                    } catch (IOException e) {
                        LOG.error("上传文件失败", e);
                        throw new FlightyThoughtException("上传文件失败", e);
                    } finally {
                        ossClient.shutdown();
                    }
                }
            }
        }
        appVersionEntity.setVersionId(versionId);
        appVersionEntity.setVersion(version);
        appVersionEntity.setCurrent(true);
        appVersionEntity.setForceUpdate(forceUpdate);
        appVersionEntity.setDescription(description);
        // 将其他数据当前版本置为false
        List<AppVersionEntity> oldEntities = appVersionRepository.findByCurrent(true);
        if (oldEntities != null && oldEntities.size() > 0) {
            oldEntities.forEach(entity -> entity.setCurrent(false));
            appVersionRepository.saveAll(oldEntities);
        }
        // 保存新数据
        appVersionEntity = appVersionRepository.save(appVersionEntity);
        return appVersionEntity;
    }

    public static String getSuffix(MultipartFile file) {
        if (file != null) {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName != null) {
                return originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            }
        }
        return null;
    }
}
