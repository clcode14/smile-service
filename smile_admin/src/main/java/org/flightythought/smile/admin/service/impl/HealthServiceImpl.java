package org.flightythought.smile.admin.service.impl;

import com.aliyun.oss.OSSClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.flightythought.smile.admin.bean.*;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.config.ALiOSSConfig;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.flightythought.smile.admin.dto.HealthWayDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CommonService;
import org.flightythought.smile.admin.service.HealthService;
import org.flightythought.smile.admin.service.SolutionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.flightythought.smile.admin.service.impl.CommonServiceImpl.getSuffix;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthServiceImpl
 * @CreateTime 2019/4/9 18:13
 * @Description: TODO
 */
@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthRepository healthRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private HealthToSolutionRepository healthToSolutionRepository;
    @Autowired
    private HealthWayRepository healthWayRepository;
    @Autowired
    private CommonService commonService;
    @Value("${html}")
    private String html;

    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;
    @Value("${oss-status}")
    private Boolean ossStatus;
    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private ALiOSSConfig aLiOSSConfig;
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private HealthWayMusicRepository healthWayMusicRepository;

    @Override
    @Transactional
    public Page<HealthClassInfo> findHealthClass(Map<String, String> params) {
        String domainPort = platformUtils.getDomainPort();
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");

        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize));
        Page<HealthEntity> healthClassEntities = healthRepository.findAll(pageRequest);
        List<HealthClassInfo> healthClassInfos = new ArrayList<>();
        healthClassEntities.forEach(healthClassEntity -> {
            HealthClassInfo healthClassInfo = new HealthClassInfo();
            // 养生大类ID
            healthClassInfo.setHealthId(healthClassEntity.getHealthId());
            // 编码
            healthClassInfo.setNumber(healthClassEntity.getNumber());
            // 养生大类名称
            healthClassInfo.setHealthName(healthClassEntity.getHealthName());
            // 背景图片URL
            ImagesEntity imagesEntity = healthClassEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo bgImage = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthClassInfo.setBgImage(bgImage);
            }
            // 内容介绍
            healthClassInfo.setContent(healthClassEntity.getContent());
            // 关联的解决方案
            List<SolutionInfo> solutionInfos = healthClassEntity.getSolutions().stream().map(solutionEntity -> solutionService.getSolutionInfo(solutionEntity)).collect(Collectors.toList());
            healthClassInfo.setSolutions(solutionInfos);
            healthClassInfos.add(healthClassInfo);
        });
        return new PageImpl<>(healthClassInfos, pageRequest, healthClassEntities.getTotalElements());
    }

    @Override
    public HealthClassInfo getHealthClass(Integer healthClassId) {
        // 获取养生大类
        HealthEntity healthEntity = healthRepository.findByHealthId(healthClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthEntity != null) {
            HealthClassInfo healthClassInfo = new HealthClassInfo();
            // 养生大类ID
            healthClassInfo.setHealthId(healthEntity.getHealthId());
            // 编码
            healthClassInfo.setNumber(healthEntity.getNumber());
            // 养生大类名称
            healthClassInfo.setHealthName(healthEntity.getHealthName());
            // 背景图片
            ImagesEntity imagesEntity = healthEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthClassInfo.setBgImage(imageInfo);
            }
            // 内容介绍
            healthClassInfo.setContent(healthEntity.getContent());
            // 关联的解决方案
            List<SolutionInfo> solutionInfos = healthEntity.getSolutions().stream().map(solutionEntity -> solutionService.getSolutionInfo(solutionEntity)).collect(Collectors.toList());
            healthClassInfo.setSolutions(solutionInfos);
            return healthClassInfo;
        }
        return null;
    }

    @Override
    public HealthEntity saveHealthClass(HealthClassDTO healthClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        HealthEntity healthEntity = new HealthEntity();
        // 背景图片ID
        healthEntity.setBgImageId(healthClassDTO.getBgImageId());
        // 内容描述
        if (StringUtils.isNotBlank(healthClassDTO.getContent())) {
            healthEntity.setContent(html + healthClassDTO.getContent());
        }
        // 养生名称
        healthEntity.setHealthName(healthClassDTO.getHealthName());
        // 编码
        healthEntity.setNumber(healthClassDTO.getNumber());
        // 创建者
        healthEntity.setCreateUserName(sysUserEntity.getUserName());
        healthEntity = healthRepository.save(healthEntity);
        // 保存关联的解决方案
        List<Integer> solutionIds = healthClassDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            HealthEntity finalHealthEntity = healthEntity;
            List<HealthToSolutionEntity> healthToSolutionEntities = solutionIds.stream().map(integer -> {
                HealthToSolutionEntity healthToSolutionEntity = new HealthToSolutionEntity();
                healthToSolutionEntity.setHealthId(finalHealthEntity.getHealthId());
                healthToSolutionEntity.setSolutionId(integer);
                return healthToSolutionEntity;
            }).collect(Collectors.toList());
            healthToSolutionRepository.saveAll(healthToSolutionEntities);
        }
        return healthEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HealthEntity modifyHealthClass(HealthClassDTO healthClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        HealthEntity healthEntity = healthRepository.findByHealthId(healthClassDTO.getHealthId());
        if (healthEntity == null) {
            throw new FlightyThoughtException("没有找到对应的养生");
        }
        if (!healthEntity.getBgImageId().equals(healthClassDTO.getBgImageId())) {
            // 删除原先背景图片
            if (healthEntity.getBgImageId() != null) {
                commonService.deleteImage(healthEntity.getBgImageId());
            }
        }
        // 背景图片
        healthEntity.setBgImageId(healthClassDTO.getBgImageId());
        // 内容描述
        if (StringUtils.isNotBlank(healthClassDTO.getContent())) {
            healthEntity.setContent(html + healthClassDTO.getContent());
        }
        // 养生名称
        healthEntity.setHealthName(healthClassDTO.getHealthName());
        // 编码
        healthEntity.setNumber(healthClassDTO.getNumber());
        // 更新者
        healthEntity.setUpdateUserName(sysUserEntity.getUserName());
        // 删除关联的解决方案
        healthToSolutionRepository.deleteAllByHealthId(healthClassDTO.getHealthId());
        // 保存关联的解决方案
        List<Integer> solutionIds = healthClassDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            List<HealthToSolutionEntity> healthToSolutionEntities = solutionIds.stream().map(integer -> {
                HealthToSolutionEntity healthToSolutionEntity = new HealthToSolutionEntity();
                healthToSolutionEntity.setHealthId(healthEntity.getHealthId());
                healthToSolutionEntity.setSolutionId(integer);
                return healthToSolutionEntity;
            }).collect(Collectors.toList());
            healthToSolutionRepository.saveAll(healthToSolutionEntities);
        }
        return healthRepository.save(healthEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHealthClass(Integer healthId, HttpSession session) {
        HealthEntity healthEntity = healthRepository.findByHealthId(healthId);
        if (healthEntity != null) {
            if (healthEntity.getBgImageId() != null) {
                commonService.deleteImage(healthEntity.getBgImageId());
            }
            healthRepository.deleteByHealthId(healthId);
        }
    }

    @Override
    @Transactional
    public HealthWayEntity addHealthWay(HealthWayDTO healthWayDTO) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        HealthWayEntity healthWayEntity = new HealthWayEntity();
        // 养生方式名称
        healthWayEntity.setWayName(healthWayDTO.getWayName());
        // 背景图片
        healthWayEntity.setBgImageId(healthWayDTO.getBgImageId());
        // 编码
        healthWayEntity.setNumber(healthWayDTO.getNumber());
        // 内容
        if (StringUtils.isNotBlank(healthWayDTO.getContent())) {
            healthWayEntity.setContent(html + healthWayDTO.getContent());
        }
        // 音乐链接
        healthWayEntity.setMusicUrl(healthWayDTO.getMusicUrl());
        // 类型
        healthWayEntity.setType(healthWayDTO.getType());
        List<HealthWayMusicEntity> healthWayMusicEntities = new ArrayList<>();
        List<FilesEntity> filesEntities = filesRepository.findByIdIn(healthWayDTO.getMusicFileIds());
        if (filesEntities != null && filesEntities.size() > 0) {
            if (healthWayDTO.getType() == 1) {
                // 类型1 只有一个音乐
                FilesEntity filesEntity = filesEntities.get(0);
                HealthWayMusicEntity healthWayMusicEntity = new HealthWayMusicEntity();
                healthWayMusicEntity.setFileId(filesEntity.getId());
                healthWayMusicEntity.setUrl(filesEntity.getOssUrl());
                healthWayMusicEntity.setName(filesEntity.getFileName());
                healthWayMusicEntities.add(healthWayMusicEntity);
            }
            if (healthWayDTO.getType() == 2) {
                // 类型2 快、中、慢 三种音乐
                for (int i = 0; i < filesEntities.size(); i++) {
                    FilesEntity filesEntity = filesEntities.get(i);
                    HealthWayMusicEntity healthWayMusicEntity = new HealthWayMusicEntity();
                    healthWayMusicEntity.setFileId(filesEntity.getId());
                    healthWayMusicEntity.setUrl(filesEntity.getOssUrl());
                    String name = null;
                    switch (i) {
                        case 0:
                            name = "快速";
                            break;
                        case 1:
                            name = "中速";
                            break;
                        case 2:
                            name = "慢速";
                            break;
                    }
                    healthWayMusicEntity.setName(name);
                    healthWayMusicEntities.add(healthWayMusicEntity);
                }
            }
            if (healthWayDTO.getType() == 3) {
                // 类型3 多种音乐
                for (FilesEntity filesEntity : filesEntities) {
                    HealthWayMusicEntity healthWayMusicEntity = new HealthWayMusicEntity();
                    healthWayMusicEntity.setFileId(filesEntity.getId());
                    healthWayMusicEntity.setUrl(filesEntity.getOssUrl());
                    healthWayMusicEntity.setName(filesEntity.getFileName());
                    healthWayMusicEntities.add(healthWayMusicEntity);
                }
            }
            healthWayEntity.setMusicEntities(healthWayMusicEntities);
        }

        // 创建者
        healthWayEntity.setCreateUserName(userEntity.getLoginName());
        return healthWayRepository.save(healthWayEntity);
    }

    @Override
    public Page<HealthWay> getHealthWays(Map<String, String> params) {
        String domainPort = platformUtils.getDomainPort();
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");

        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize));
        Page<HealthWayEntity> healthWayEntities = healthWayRepository.findAll(pageable);
        List<HealthWay> healthWays = healthWayEntities.stream().map(healthWayEntity -> {
            HealthWay healthWay = new HealthWay();
            BeanUtils.copyProperties(healthWayEntity, healthWay);
            // 背景图
            ImagesEntity imagesEntity = healthWayEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthWay.setBgImage(imageInfo);
            }
            // 音乐
            List<HealthWayMusicEntity> healthWayMusicEntities = healthWayEntity.getMusicEntities();
            if (healthWayMusicEntities != null) {
                List<FilesEntity> filesEntities = healthWayMusicEntities.stream().map(HealthWayMusicEntity::getMusicFile).collect(Collectors.toList());
                List<FileInfo> fileInfos = filesEntities.stream().map(filesEntity -> platformUtils.getFileInfo(filesEntity, domainPort)).collect(Collectors.toList());
                healthWay.setMusic(fileInfos);
            }
            return healthWay;
        }).collect(Collectors.toList());
        return new PageImpl<>(healthWays, pageable, healthWayEntities.getTotalElements());
    }

    @Override
    @Transactional
    public HealthWayEntity modifyHealthWay(HealthWayDTO healthWayDTO) {
        // 获取养生方式
        HealthWayEntity healthWayEntity = healthWayRepository.findByHealthWayId(healthWayDTO.getHealthWayId());
        // 删除关联音乐
        List<HealthWayMusicEntity> musicEntities = healthWayMusicRepository.findByHealthWayId(healthWayEntity.getHealthWayId());
        if (musicEntities != null && musicEntities.size() > 0) {
            healthWayMusicRepository.deleteAll(musicEntities);
        }
        if (healthWayEntity != null) {
            SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
            // 养生方式名称
            healthWayEntity.setWayName(healthWayDTO.getWayName());
            if (!healthWayEntity.getBgImage().equals(healthWayDTO.getBgImageId())) {
                // 删除原来的背景图片
                commonService.deleteImage(healthWayEntity.getBgImageId());
            }
            // 背景图片
            healthWayEntity.setBgImageId(healthWayDTO.getBgImageId());
            // 编码
            healthWayEntity.setNumber(healthWayDTO.getNumber());
            // 内容
            if (StringUtils.isNotBlank(healthWayDTO.getContent())) {
                healthWayEntity.setContent(html + healthWayDTO.getContent());
            }
            // 音乐链接
            healthWayEntity.setMusicUrl(healthWayDTO.getMusicUrl());
            // 类型
            healthWayEntity.setType(healthWayDTO.getType());
            List<HealthWayMusicEntity> healthWayMusicEntities = new ArrayList<>();
            List<FilesEntity> filesEntities = filesRepository.findByIdIn(healthWayDTO.getMusicFileIds());
            if (filesEntities != null && filesEntities.size() > 0) {
                if (healthWayDTO.getType() == 1) {
                    // 类型1 只有一个音乐
                    FilesEntity filesEntity = filesEntities.get(0);
                    HealthWayMusicEntity healthWayMusicEntity = new HealthWayMusicEntity();
                    healthWayMusicEntity.setFileId(filesEntity.getId());
                    healthWayMusicEntity.setUrl(filesEntity.getOssUrl());
                    healthWayMusicEntity.setName(filesEntity.getFileName());
                    healthWayMusicEntities.add(healthWayMusicEntity);
                }
                if (healthWayDTO.getType() == 2) {
                    // 类型2 快、中、慢 三种音乐
                    for (int i = 0; i < filesEntities.size(); i++) {
                        FilesEntity filesEntity = filesEntities.get(i);
                        HealthWayMusicEntity healthWayMusicEntity = new HealthWayMusicEntity();
                        healthWayMusicEntity.setFileId(filesEntity.getId());
                        healthWayMusicEntity.setUrl(filesEntity.getOssUrl());
                        String name = null;
                        switch (i) {
                            case 0:
                                name = "快速";
                                break;
                            case 1:
                                name = "中速";
                                break;
                            case 2:
                                name = "慢速";
                                break;
                        }
                        healthWayMusicEntity.setName(name);
                        healthWayMusicEntities.add(healthWayMusicEntity);
                    }
                }
                if (healthWayDTO.getType() == 3) {
                    // 类型3 多种音乐
                    for (FilesEntity filesEntity : filesEntities) {
                        HealthWayMusicEntity healthWayMusicEntity = new HealthWayMusicEntity();
                        healthWayMusicEntity.setFileId(filesEntity.getId());
                        healthWayMusicEntity.setUrl(filesEntity.getOssUrl());
                        healthWayMusicEntity.setName(filesEntity.getFileName());
                        healthWayMusicEntities.add(healthWayMusicEntity);
                    }
                }
                healthWayEntity.setMusicEntities(healthWayMusicEntities);
            }
            // 修改者
            healthWayEntity.setUpdateUserName(userEntity.getLoginName());
            healthWayRepository.save(healthWayEntity);
            return healthWayEntity;
        }
        return null;
    }

    @Override
    public void deleteHealthWay(Integer healthWayId) {
        HealthWayEntity healthWayEntity = healthWayRepository.findByHealthWayId(healthWayId);
        if (healthWayEntity != null) {
            // 删除背景图片
            if (healthWayEntity.getBgImage() != null) {
                commonService.deleteImage(healthWayEntity.getBgImageId());
            }
            healthWayRepository.delete(healthWayEntity);
        }
    }

    @Override
    public FileInfo uploadFile(MultipartFile multipartFile, String musicName, HttpSession session) {
        String fileType = "music";
        // 获取当前登陆用户
        SysUserEntity userEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        String imagePath = "", userPath;
        if (ossStatus) {
            userPath = fileType + "/" + userEntity.getId();
        } else {
            userPath = File.separator + fileType + File.separator + userEntity.getId();
        }
        if (!ossStatus) {
            // 获取系统参数
            SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
            if (sysParameterEntity == null) {
                throw new FlightyThoughtException("请设置上传文件路径系统参数");
            } else {
                imagePath = sysParameterEntity.getParameterValue();
                File file = new File(imagePath + userPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        }

        if (multipartFile != null) {
            FilesEntity filesEntity = new FilesEntity();
            // 上传者
            filesEntity.setCreateUserName(userEntity.getLoginName());
            // module
            filesEntity.setModule("3");
            // 图片大小
            filesEntity.setSize(multipartFile.getSize());
            // 文件类型
            filesEntity.setFileType(multipartFile.getContentType());
            // 上传封面图片到服务器
            // 获取原始图片名称
            String filename = multipartFile.getOriginalFilename();
            // 新建文件
            if (filename != null) {
                // 音乐名称
                filesEntity.setFileName(musicName);
                if (!ossStatus) {
                    String path = userPath + File.separator + System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));
                    // 音乐路径
                    filesEntity.setPath(path);
                    File coverPictureFile = new File(imagePath + path);
                    try {
                        // 创建文件输出流
                        FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                        // 复制文件
                        IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        throw new FlightyThoughtException("上传图片失败", e);
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
                        filesEntity.setOssKey(fileKey);
                        filesEntity.setOssUrl(url.toString());
                    } catch (IOException e) {
                        throw new FlightyThoughtException("上传图片失败", e);
                    } finally {
                        ossClient.shutdown();
                    }
                }
            }
            filesEntity = filesRepository.save(filesEntity);
            return platformUtils.getFileInfo(filesEntity, domainPort);
        }
        return null;
    }

    @Override
    public List<FileInfo> getAllMusicFile() {
        List<FilesEntity> filesEntities = filesRepository.findByModule("3");
        if (filesEntities != null) {
            String domainPort = platformUtils.getDomainPort();
            return filesEntities.stream().map(filesEntity -> platformUtils.getFileInfo(filesEntity, domainPort)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Page<FileInfo> getMusics(Integer pageNumber, Integer pageSize, String musicName) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort);
        Specification<FilesEntity> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            // 音乐名称
            if (StringUtils.isNotBlank(musicName)) {
                predicate.getExpressions().add(criteriaBuilder.like(root.get("fileName"), musicName));
            }
            predicate.getExpressions().add(criteriaBuilder.equal(root.get("module"), "3"));
            return predicate;
        };
        Page<FilesEntity> filesEntities = filesRepository.findAll(specification, pageRequest);
        String domainPort = platformUtils.getDomainPort();
        List<FileInfo> fileInfos = filesEntities.stream().map(filesEntity -> platformUtils.getFileInfo(filesEntity, domainPort)).collect(Collectors.toList());
        return new PageImpl<>(fileInfos, pageRequest, filesEntities.getTotalElements());
    }

    @Override
    public void deleteFile(Integer fileId) {
        if (fileId != null) {
            // 根据imageId获取图片对象
            FilesEntity filesEntity = filesRepository.findById(fileId);
            if (filesEntity != null) {
                if (ossStatus) {
                    // OSS 对象存储
                    String endpoint = aLiOSSConfig.getEndpoint();
                    String accessKeyId = aLiOSSConfig.getAccessKeyId();
                    String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
                    String bucketName = aLiOSSConfig.getBucketName();
                    // 创建OSSClient实例
                    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                    // objectName
                    String objectName = filesEntity.getOssKey();
                    if (StringUtils.isNotBlank(objectName)) {
                        boolean found = ossClient.doesObjectExist(bucketName, objectName);
                        if (found) {
                            ossClient.deleteObject(bucketName, objectName);
                        }
                    }
                    ossClient.shutdown();
                } else {
                    // 删除文件
                    String path = sysParameterRepository.getFilePathParam().getParameterValue() + filesEntity.getPath();
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                filesRepository.delete(filesEntity);
            }
        }
    }
}
