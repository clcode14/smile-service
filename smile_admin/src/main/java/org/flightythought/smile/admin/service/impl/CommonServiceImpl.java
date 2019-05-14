package org.flightythought.smile.admin.service.impl;

import com.aliyun.oss.OSSClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.config.ALiOSSConfig;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysParameterEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.ImagesRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CommonServiceImpl.java
 * @CreateTime 2019/5/6 15:20
 * @Description: TODO
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private ALiOSSConfig aLiOSSConfig;

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;
    @Value("${oss-status}")
    private Boolean ossStatus;

    @Override
    public ImageInfo uploadImage(MultipartFile image, String type, HttpSession session) throws FlightyThoughtException {
        String imageFileType = "";
        if (type != null) {
            switch (type) {
                case "1": {
                    // 课程图片
                    imageFileType = "course_image";
                    break;
                }
                case "2": {
                    // 解决方案
                    imageFileType = "solution";
                    break;
                }
                case "3": {
                    // 疾病配置
                    imageFileType = "disease";
                    break;
                }
                case "4": {
                    // 相关机构
                    imageFileType = "office";
                    break;
                }
                default:
                    imageFileType = "";
            }
        }
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        ImagesEntity imagesEntity;
        String imageName;
        if (image != null) {
            imagesEntity = new ImagesEntity();
            // 上传者
            imagesEntity.setCreateUserName(sysUserEntity.getLoginName());
            // 图片大小
            imagesEntity.setSize(image.getSize());
            // 获取原始图片名称
            imageName = image.getOriginalFilename();
            // 图片名称
            imagesEntity.setFileName(imageName);
        } else {
            throw new FlightyThoughtException("请选择要上传的图片");
        }
        String imagePath, userPath;
        if (!ossStatus) {
            // 非OSS存储需要获取系统参数
            SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
            SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
            String domainPort = domainPortEntity.getParameterValue();
            if (sysParameterEntity == null) {
                throw new FlightyThoughtException("请设置上传文件路径系统参数");
            } else {
                imagePath = sysParameterEntity.getParameterValue();
                if (!"".equals(imageFileType)) {
                    userPath = File.separator + imageFileType + File.separator + sysUserEntity.getId() + "_system";
                } else {
                    userPath = File.separator + sysUserEntity.getId() + "_system";
                }
                File file = new File(imagePath + userPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            // 新建文件
            if (imageName != null) {
                // 本地服务器存储
                String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                // 图片路径
                imagesEntity.setPath(path);
                File coverPictureFile = new File(imagePath + path);
                try {
                    // 创建文件输出流
                    FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                    // 复制文件
                    IOUtils.copy(image.getInputStream(), fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    // 保存数据对象
                    imagesEntity = imagesRepository.save(imagesEntity);
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setId(imagesEntity.getId());
                    imageInfo.setName(imagesEntity.getFileName());
                    imageInfo.setSize(imagesEntity.getSize());
                    String imageUrl = domainPort + contentPath + staticUrl + imagesEntity.getPath();
                    imageInfo.setUrl(imageUrl.replace("\\", "/"));
                    return imageInfo;
                } catch (IOException e) {
                    LOG.error("上传图片失败", e);
                    throw new FlightyThoughtException("上传图片失败", e);
                }
            }
        } else {
            // OSS 对象存储
            // 1. 获取文件后缀名
            String suffix = getSuffix(image);
            // 2. 获取OSS参数信息
            String endpoint = aLiOSSConfig.getEndpoint();
            String accessKeyId = aLiOSSConfig.getAccessKeyId();
            String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
            String bucketName = aLiOSSConfig.getBucketName();
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            if (!"".equals(imageFileType)) {
                userPath = imageFileType + "/" + sysUserEntity.getId() + "_system";
            } else {
                userPath = sysUserEntity.getId() + "_system";
            }
            // 文件KEY
            String fileKey = userPath + "/" + System.currentTimeMillis() + "." + suffix;
            // 上传文件
            try {
                ossClient.putObject(bucketName, fileKey, image.getInputStream());
                // 设置URL过期时间为100年
                Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365 * 100);

                // 生成URL
                URL url = ossClient.generatePresignedUrl(bucketName, fileKey, expiration);
                // 保存数据对象
                imagesEntity.setOssKey(fileKey);
                imagesEntity.setOssUrl(url.toString());
                imagesEntity = imagesRepository.save(imagesEntity);
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setName(imagesEntity.getFileName());
                imageInfo.setSize(imagesEntity.getSize());
                imageInfo.setUrl(url.toString());
                return imageInfo;
            } catch (IOException e) {
                LOG.error("上传图片失败", e);
                throw new FlightyThoughtException("上传图片失败", e);
            } finally {
                ossClient.shutdown();
            }
        }
        return null;
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

    @Override
    public List<ImageInfo> uploadImages(List<MultipartFile> images, String type, HttpSession session) throws FlightyThoughtException {
        if (images != null && images.size() > 0) {
            return images.stream().map(file -> uploadImage(file, type, session)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void deleteImage(Integer imageId) {
        if (imageId != null) {
            // 根据imageId获取图片对象
            ImagesEntity imagesEntity = imagesRepository.findById(imageId);
            if (imagesEntity != null) {
                if (ossStatus) {
                    // OSS 对象存储
                    String endpoint = aLiOSSConfig.getEndpoint();
                    String accessKeyId = aLiOSSConfig.getAccessKeyId();
                    String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
                    String bucketName = aLiOSSConfig.getBucketName();
                    // 创建OSSClient实例
                    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                    // objectName
                    String objectName = imagesEntity.getOssKey();
                    ossClient.deleteObject(bucketName, objectName);
                    ossClient.shutdown();
                } else {
                    // 删除文件
                    String path = sysParameterRepository.getFilePathParam().getParameterValue() + imagesEntity.getPath();
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                imagesRepository.delete(imagesEntity);
            }
        }
    }
}
