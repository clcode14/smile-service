package org.flightythought.smile.appserver.service.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.SysParameterEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.DiseaseClassDetailRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private PlatformUtils platformUtils;

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;

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
        // 获取系统参数
        SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String imagePath, userPath;
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
        if (image != null) {
            ImagesEntity imagesEntity = new ImagesEntity();
            // 上传者
            imagesEntity.setCreateUserName(userEntity.getId() + "");
            // 图片大小
            imagesEntity.setSize(image.getSize());
            // 上传封面图片到服务器
            // 获取原始图片名称
            String imageName = image.getOriginalFilename();
            // 新建文件
            if (imageName != null) {
                String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                // 图片名称
                imagesEntity.setFileName(imageName);
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
            throw new FlightyThoughtException("请选择要上传的图片");
        }
        return null;
    }

    @Override
    public List<ImageInfo> uploadImages(List<MultipartFile> images, String type, HttpSession session) throws FlightyThoughtException {
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
        // 获取系统参数
        SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        String imagePath, userPath;
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
        List<ImagesEntity> imagesEntityArrayList = new ArrayList<>();
        if (images == null || images.size() == 0) {
            throw new FlightyThoughtException("请选择要上传的图片");
        }
        for (MultipartFile multipartFile : images) {
            if (multipartFile != null) {
                ImagesEntity imagesEntity1 = new ImagesEntity();
                // 上传者
                imagesEntity1.setCreateUserName(userEntity.getId() + "");
                // 图片大小
                imagesEntity1.setSize(multipartFile.getSize());
                // 上传封面图片到服务器
                // 获取原始图片名称
                String imageName = multipartFile.getOriginalFilename();
                // 新建文件
                if (imageName != null) {
                    String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                    // 图片名称
                    imagesEntity1.setFileName(imageName);
                    // 图片路径
                    imagesEntity1.setPath(path);
                    File coverPictureFile = new File(imagePath + path);
                    try {
                        // 创建文件输出流
                        FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                        // 复制文件
                        IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        imagesEntity1 = imagesRepository.save(imagesEntity1);
                        imagesEntityArrayList.add(imagesEntity1);
                    } catch (IOException e) {
                        LOG.error("上传图片失败", e);
                        throw new FlightyThoughtException("上传图片失败", e);
                    }
                }
            }
        }
        imagesEntityArrayList = imagesRepository.saveAll(imagesEntityArrayList);
        List<ImageInfo> result = new ArrayList<>();
        imagesEntityArrayList.forEach(images1 -> {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setId(images1.getId());
            imageInfo.setName(images1.getFileName());
            imageInfo.setSize(images1.getSize());
            String imageUrl = domainPort + contentPath + staticUrl + images1.getPath();
            imageInfo.setUrl(imageUrl.replace("\\", "/"));
            result.add(imageInfo);
        });
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
                    String bgImageUrl = platformUtils.getImageUrlByPath(bgImagesEntity.getPath(), domainPort);
                    diseaseClassDetailSimple.setBgImageUrl(bgImageUrl);
                }
                // 内容介绍
                diseaseClassDetailSimple.setContent(diseaseClassDetailEntity.getContent());
                // 图标URL
                ImagesEntity iconImageEntity = diseaseClassDetailEntity.getBgImage();
                if (iconImageEntity != null) {
                    String iconImageUrl = platformUtils.getImageUrlByPath(iconImageEntity.getPath(), domainPort);
                    diseaseClassDetailSimple.setIconUrl(iconImageUrl);
                }
                diseaseClassDetailSimples.add(diseaseClassDetailSimple);
            });
            return new PageImpl<>(diseaseClassDetailSimples, pageRequest, total);
        }
        return null;
    }
}
