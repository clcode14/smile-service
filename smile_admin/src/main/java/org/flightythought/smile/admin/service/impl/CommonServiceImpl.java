package org.flightythought.smile.admin.service.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.Images;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private SysParameterRepository sysParameterRepository;

    @Autowired
    private ImagesRepository imagesRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Value("${image-url}")
    private String imageRequest;
    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Override
    public ImageInfo uploadImage(MultipartFile image, String type, HttpSession session) throws FlightyThoughtException {
        String imageFileType = "";
        if (type != null) {
            switch (type) {
                case "1": {
                    imageFileType = "course_image";
                    break;
                }
                default:
                    imageFileType = "";
            }
        }
        // 获取系统参数
        SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        String imagePath, userPath;
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        if (sysParameterEntity == null) {
            throw new FlightyThoughtException("请设置上传文件路径系统参数");
        } else {
            imagePath = sysParameterEntity.getParameterValue();
            if (!"".equals(imageFileType)) {
                userPath = File.separator + imageFileType + File.separator + sysUserEntity.getId();
            } else {
                userPath = File.separator + sysUserEntity.getId();
            }
            File file = new File(imagePath + userPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        if (image != null) {
            Images images = new Images();
            // 上传者
            images.setCreateUserName(sysUserEntity.getId() + "");
            // 图片大小
            images.setSize(image.getSize());
            // 上传封面图片到服务器
            // 获取原始图片名称
            String imageName = image.getOriginalFilename();
            // 新建文件
            if (imageName != null) {
                String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                // 图片名称
                images.setFileName(imageName);
                // 图片路径
                images.setPath(path);
                File coverPictureFile = new File(imagePath + path);
                try {
                    // 创建文件输出流
                    FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                    // 复制文件
                    IOUtils.copy(image.getInputStream(), fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    images = imagesRepository.save(images);
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setId(images.getId());
                    imageInfo.setName(images.getFileName());
                    imageInfo.setSize(images.getSize());
                    String imageUrl = domainPort + contentPath + imageRequest + images.getPath();
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
                case "1": {
                    imageFileType = "course_image";
                    break;
                }
                default:
                    imageFileType = "";
            }
        }
        // 获取系统参数
        SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        String imagePath, userPath;
        if (sysParameterEntity == null) {
            throw new FlightyThoughtException("请设置上传文件路径系统参数");
        } else {
            imagePath = sysParameterEntity.getParameterValue();
            if (!"".equals(imageFileType)) {
                userPath = File.separator + imageFileType + File.separator + sysUserEntity.getId();
            } else {
                userPath = File.separator + sysUserEntity.getId();
            }
            File file = new File(imagePath + userPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        List<Images> imagesArrayList = new ArrayList<>();
        if (images == null || images.size() == 0) {
            throw new FlightyThoughtException("请选择要上传的图片");
        }
        for (MultipartFile multipartFile : images) {
            if (multipartFile != null) {
                Images images1 = new Images();
                // 上传者
                images1.setCreateUserName(sysUserEntity.getId() + "");
                // 图片大小
                images1.setSize(multipartFile.getSize());
                // 上传封面图片到服务器
                // 获取原始图片名称
                String imageName = multipartFile.getOriginalFilename();
                // 新建文件
                if (imageName != null) {
                    String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                    // 图片名称
                    images1.setFileName(imageName);
                    // 图片路径
                    images1.setPath(path);
                    File coverPictureFile = new File(imagePath + path);
                    try {
                        // 创建文件输出流
                        FileOutputStream fileOutputStream = new FileOutputStream(coverPictureFile);
                        // 复制文件
                        IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        images1 = imagesRepository.save(images1);
                        imagesArrayList.add(images1);
                    } catch (IOException e) {
                        LOG.error("上传图片失败", e);
                        throw new FlightyThoughtException("上传图片失败", e);
                    }
                }
            }
        }
        imagesArrayList = imagesRepository.saveAll(imagesArrayList);
        List<ImageInfo> result = new ArrayList<>();
        imagesArrayList.forEach(images1 -> {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setId(images1.getId());
            imageInfo.setName(images1.getFileName());
            imageInfo.setSize(images1.getSize());
            String imageUrl = domainPort + contentPath + imageRequest + images1.getPath();
            imageInfo.setUrl(imageUrl.replace("\\", "/"));
            result.add(imageInfo);
        });
        return result;
    }
}
