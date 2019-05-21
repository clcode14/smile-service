package org.flightythought.smile.appserver.service.impl;

import com.aliyun.oss.OSSClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.config.ALiOSSConfig;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.DiseaseClassDetailRepository;
import org.flightythought.smile.appserver.database.repository.FilesRepository;
import org.flightythought.smile.appserver.database.repository.ImagesRepository;
import org.flightythought.smile.appserver.database.repository.SysParameterRepository;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private PlatformUtils platformUtils;
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
                case "1001": {
                    imageFileType = "charity";
                    break;
                }
                case "1002": {
                    imageFileType = "fault";
                    break;
                }
                default:
                    imageFileType = "";
            }
        }
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        ImagesEntity imagesEntity;
        String imageName;
        if (image != null) {
            imagesEntity = new ImagesEntity();
            // 上传者
            imagesEntity.setCreateUserName(userEntity.getId() + "");
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
            // 获取系统参数
            SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
            SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
            String domainPort = domainPortEntity.getParameterValue();
            if (sysParameterEntity == null) {
                throw new FlightyThoughtException("请设置上传文件路径系统参数");
            } else {
                imagePath = sysParameterEntity.getParameterValue();
                if (!"".equals(imageFileType)) {
                    userPath = File.separator + imageFileType + File.separator + userEntity.getId();
                } else {
                    userPath = File.separator + userEntity.getId();
                }
                File file = new File(imagePath + userPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            if (imageName != null) {
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
                userPath = imageFileType + "/" + userEntity.getId();
            } else {
                userPath = userEntity.getId() + "";
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
    public FileInfo uploadFile(MultipartFile multipartFile, String type, HttpSession session) throws FlightyThoughtException {
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(multipartFile);
        List<FileInfo> fileInfos = uploadFiles(multipartFiles, type, session);
        if (fileInfos != null && fileInfos.size() > 0) {
            return fileInfos.get(0);
        }
        return null;
    }

    @Override
    public List<FileInfo> uploadFiles(List<MultipartFile> files, String type, HttpSession session) throws FlightyThoughtException {
        String fileType = "";
        if (type != null) {
            switch (type) {
                case "1": {
                    fileType = "dynamic";
                    break;
                }
                case "2": {
                    fileType = "dynamic_detail";
                    break;
                }
                default:
                    fileType = "";
            }
        }
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        List<FilesEntity> filesEntities = new ArrayList<>();
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        String imagePath = "", userPath;
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
                imagePath = sysParameterEntity.getParameterValue();
                File file = new File(imagePath + userPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        }

        if (files == null || files.size() == 0) {
            throw new FlightyThoughtException("请选择要上传的图片");
        }
        for (MultipartFile multipartFile : files) {
            if (multipartFile != null) {
                FilesEntity filesEntity = new FilesEntity();
                // 上传者
                filesEntity.setCreateUserName(userEntity.getId() + "");
                // 图片大小
                filesEntity.setSize(multipartFile.getSize());
                // 文件类型
                filesEntity.setFileType(multipartFile.getContentType());
                // 上传封面图片到服务器
                // 获取原始图片名称
                String imageName = multipartFile.getOriginalFilename();
                // 新建文件
                if (imageName != null) {
                    if (!ossStatus) {
                        String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                        // 图片名称
                        filesEntity.setFileName(imageName);
                        // 图片路径
                        filesEntity.setPath(path);
                        File coverPictureFile = new File(imagePath + path);
                        try {
                            // 创建文件输出流
                            FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                            // 复制文件
                            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            filesEntities.add(filesEntity);
                        } catch (IOException e) {
                            LOG.error("上传图片失败", e);
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
                            filesEntities.add(filesEntity);
                        } catch (IOException e) {
                            LOG.error("上传图片失败", e);
                            throw new FlightyThoughtException("上传图片失败", e);
                        } finally {
                            ossClient.shutdown();
                        }
                    }
                }
            }
        }
        filesEntities = filesRepository.saveAll(filesEntities);
        List<FileInfo> result = new ArrayList<>();
        filesEntities.forEach(filesEntity -> result.add(platformUtils.getFileInfo(filesEntity, domainPort)));
        return result;
    }

    @Override
    @Transactional
    public Page<DiseaseClassDetailSimple> getDiseaseClassDetails(PageFilterDTO pageFilterDTO) {
        Integer pageSize = pageFilterDTO.getPageSize();
        Integer pageNumber = pageFilterDTO.getPageNumber();
        List<DiseaseClassDetailEntity> diseaseClassDetailEntities;
        PageRequest pageRequest;
        Long total;
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            // 不分页
            diseaseClassDetailEntities = diseaseClassDetailRepository.findAll();
            pageRequest = PageRequest.of(0, diseaseClassDetailEntities.size());
            total = (long) diseaseClassDetailEntities.size();
        } else {
            // 分页
            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<DiseaseClassDetailEntity> diseaseClassDetailEntityPage = diseaseClassDetailRepository.findAll(pageRequest);
            diseaseClassDetailEntities = diseaseClassDetailEntityPage.getContent();
            total = diseaseClassDetailEntityPage.getTotalElements();
        }
        String domainPort = platformUtils.getDomainPort();
        List<DiseaseClassDetailSimple> diseaseClassDetailSimples = new ArrayList<>();
        if (diseaseClassDetailEntities.size() > 0) {
            diseaseClassDetailEntities.forEach(diseaseClassDetailEntity -> {
                DiseaseClassDetailSimple diseaseClassDetailSimple = new DiseaseClassDetailSimple();
                // 疾病小类ID
                diseaseClassDetailSimple.setDiseaseDetailId(diseaseClassDetailEntity.getDiseaseDetailId());
                // 疾病大类ID
                diseaseClassDetailSimple.setDiseaseId(diseaseClassDetailEntity.getDiseaseId());
                // 疾病大类名称
//                diseaseClassDetailSimple.setDiseaseClassName();
                // 疾病小类名称
                diseaseClassDetailSimple.setDiseaseClassDetailName(diseaseClassDetailEntity.getDiseaseDetailName());
                // 背景图片URL
                ImagesEntity bgImagesEntity = diseaseClassDetailEntity.getBgImage();
                if (bgImagesEntity != null) {
                    String bgImageUrl = platformUtils.getImageInfo(bgImagesEntity, domainPort).getUrl();
                    diseaseClassDetailSimple.setBgImageUrl(bgImageUrl);
                }
                // 内容介绍
                diseaseClassDetailSimple.setContent(diseaseClassDetailEntity.getContent());
                // 图标URL
                ImagesEntity iconImageEntity = diseaseClassDetailEntity.getBgImage();
                if (iconImageEntity != null) {
                    String iconImageUrl = platformUtils.getImageInfo(iconImageEntity, domainPort).getUrl();
                    diseaseClassDetailSimple.setIconUrl(iconImageUrl);
                }
                diseaseClassDetailSimples.add(diseaseClassDetailSimple);
            });
            return new PageImpl<>(diseaseClassDetailSimples, pageRequest, total);
        }
        return null;
    }
}
