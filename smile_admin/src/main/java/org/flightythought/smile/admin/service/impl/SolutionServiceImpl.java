package org.flightythought.smile.admin.service.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.admin.database.repository.SolutionImageRepository;
import org.flightythought.smile.admin.database.repository.SolutionRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.SolutionDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionServiceImpl
 * @CreateTime 2019/3/27 19:38
 * @Description: TODO
 */
@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private SolutionImageRepository solutionImageRepository;

    private static final String SOLUTION_IMAGE_PATH = "solutionimage";

    @Override
    public List<SelectItemOption> getCourseItems() {
        List<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findAll();
        List<SelectItemOption> selectItemOptions = new ArrayList<>();
        courseRegistrationEntities.forEach(courseRegistrationEntity -> {
            selectItemOptions.add(new SelectItemOption(courseRegistrationEntity.getCourseId() + "", courseRegistrationEntity.getTitle()));
        });
        return selectItemOptions;
    }

    @Override
    @Transactional
    public SolutionEntity addSolution(SolutionDTO solutionDTO, List<MultipartFile> images, HttpSession session) throws FlightyThoughtException {
        // 获取系统参数
        SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 创建解决方案对象
        SolutionEntity solutionEntity = new SolutionEntity();
        // 编号
        solutionEntity.setNumber(solutionDTO.getNumber());
        // 解决方案内容
        solutionEntity.setContent(solutionDTO.getContent());
        // 解决方案名称
        solutionEntity.setTitle(solutionDTO.getTitle());
//        // 课程ID
//        solutionEntity.setCourseId(solutionDTO.getCourseId());
        // 创建者
        solutionEntity.setCreateUserName(sysUserEntity.getLoginName());
        String imagePath, userPath;
        if (sysParameterEntity == null) {
            throw new FlightyThoughtException("请设置上传文件路径系统参数");
        } else {
            imagePath = sysParameterEntity.getParameterValue();
            userPath = File.separator + SOLUTION_IMAGE_PATH + File.separator + sysUserEntity.getId();
            File file = new File(imagePath + userPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        List<SolutionImage> solutionImages = new ArrayList<>();
        for (MultipartFile image : images) {
            SolutionImage solutionImage = new SolutionImage();
            if (image != null) {
                // 上传封面图片到服务器
                // 获取原始图片名称
                String imageName = image.getOriginalFilename();
                // 新建文件
                if (imageName != null) {
                    String path = userPath + File.separator + System.currentTimeMillis() + imageName.substring(imageName.lastIndexOf("."));
                    // 图片名称
                    solutionImage.setImageName(imageName);
                    // 图片路径
                    solutionImage.setPath(path);
                    // 文件大小
                    solutionImage.setSize(image.getSize());
                    File imageFile = new File(imagePath + path);
                    try {
                        // 创建文件输出流
                        FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
                        // 复制文件
                        IOUtils.copy(image.getInputStream(), fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            solutionImages.add(solutionImage);
        }
        // 保存解决方案
        SolutionEntity newEntity = solutionRepository.save(solutionEntity);
        solutionImages.forEach(solutionImage -> solutionImage.setSolutionId(newEntity.getId()));
        solutionImageRepository.saveAll(solutionImages);
        return solutionRepository.findById(newEntity.getId());
    }
}
