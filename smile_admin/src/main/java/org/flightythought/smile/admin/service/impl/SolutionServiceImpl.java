package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.dto.ImageDTO;
import org.flightythought.smile.admin.dto.SolutionDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private SolutionCourseRepository solutionCourseRepository;

    @Override
    public List<SelectItemOption> getCourseItems() {
        List<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findAll();
        List<SelectItemOption> selectItemOptions = new ArrayList<>();
        courseRegistrationEntities.forEach(courseRegistrationEntity -> selectItemOptions.add(new SelectItemOption(courseRegistrationEntity.getCourseId() + "", courseRegistrationEntity.getTitle())));
        return selectItemOptions;
    }

    @Override
    @Transactional
    public SolutionEntity saveSolution(SolutionDTO solutionDTO, HttpSession session) throws FlightyThoughtException {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 获取解决方案ID
        Integer solutionId = solutionDTO.getId();
        boolean isUpdate = false;
        if (solutionId != null && solutionId != 0) {
            isUpdate = true;
        }
        SolutionEntity solutionEntity;
        if (isUpdate) {
            // 修改解决方案
            solutionEntity = solutionRepository.findById(solutionId);
            // 修改者
            solutionEntity.setUpdateUserName(sysUserEntity.getLoginName());
            // 删除相关课程和相关配图
            solutionImageRepository.deleteAllBySolutionId(solutionId);
            solutionCourseRepository.deleteAllBySolutionId(solutionId);
        } else {
            // 新增解决方案
            solutionEntity = new SolutionEntity();
            // 创建者
            solutionEntity.setCreateUserName(sysUserEntity.getLoginName());
        }
        if (solutionEntity != null) {
            // 编码
            solutionEntity.setNumber(solutionDTO.getNumber());
            // 标题
            solutionEntity.setTitle(solutionDTO.getTitle());
            // 内容
            solutionEntity.setContent(solutionDTO.getContent());
            // 机构ID
            // 保存解决方案
            solutionRepository.save(solutionEntity);
            // 获取解决方案ID
            solutionId = solutionEntity.getId();
            // 获取课程ID
            List<Integer> courseIds = solutionDTO.getCourseIds();
            List<SolutionCourseEntity> solutionCourseEntities = new ArrayList<>();
            Integer finalSolutionId = solutionId;
            courseIds.forEach(courseId -> {
                SolutionCourseEntity solutionCourseEntity = new SolutionCourseEntity();
                solutionCourseEntity.setCourseId(courseId);
                solutionCourseEntity.setSolutionId(finalSolutionId);
                solutionCourseEntities.add(solutionCourseEntity);
            });
            solutionCourseRepository.saveAll(solutionCourseEntities);
            // 获取解决方案配图
            List<ImageDTO> imageDTOS = solutionDTO.getImages();
            List<SolutionImageEntity> solutionImageEntities = new ArrayList<>();
            imageDTOS.forEach(imageDTO -> {
                SolutionImageEntity solutionImageEntity = new SolutionImageEntity();
                solutionImageEntity.setSolutionId(finalSolutionId);
                solutionImageEntity.setImageId(imageDTO.getImageId());
                solutionImageEntities.add(solutionImageEntity);
            });
            solutionImageRepository.saveAll(solutionImageEntities);
            return solutionRepository.findById(solutionEntity.getId());
        }
        return null;
    }
}
