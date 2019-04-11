package org.flightythought.smile.admin.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.dto.ImageDTO;
import org.flightythought.smile.admin.dto.SolutionDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SolutionOfficeRepository solutionOfficeRepository;

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
            solutionOfficeRepository.deleteAllBySolutionId(solutionId);
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

            //获取相关机构
            List<SolutionOfficeEntity> solutionOfficeEntities = Lists.newArrayList();
            List<Integer> officeIds = solutionDTO.getOfficeIds();
            officeIds.forEach(officeId -> {
                SolutionOfficeEntity solutionOfficeEntity = new SolutionOfficeEntity();
                solutionOfficeEntity.setOfficeId(officeId);
                solutionOfficeEntity.setSolutionId(finalSolutionId);
                solutionOfficeEntities.add(solutionOfficeEntity);
            });
            solutionOfficeRepository.saveAll(solutionOfficeEntities);

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

    @Override
    public SolutionEntity modifySolution(SolutionDTO solutionDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 获取解决方案ID
        Integer solutionId = solutionDTO.getId();
        SolutionEntity solutionEntity;
        // 修改解决方案
        solutionEntity = solutionRepository.findById(solutionId);
        // 修改者
        solutionEntity.setUpdateUserName(sysUserEntity.getLoginName());
        // 删除相关课程和相关配图
        solutionImageRepository.deleteAllBySolutionId(solutionId);
        solutionCourseRepository.deleteAllBySolutionId(solutionId);
        solutionOfficeRepository.deleteAllBySolutionId(solutionId);

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

            //获取相关机构
            List<SolutionOfficeEntity> solutionOfficeEntities = Lists.newArrayList();
            List<Integer> officeIds = solutionDTO.getOfficeIds();
            officeIds.forEach(officeId -> {
                SolutionOfficeEntity solutionOfficeEntity = new SolutionOfficeEntity();
                solutionOfficeEntity.setOfficeId(officeId);
                solutionOfficeEntity.setSolutionId(finalSolutionId);
                solutionOfficeEntities.add(solutionOfficeEntity);
            });
            solutionOfficeRepository.saveAll(solutionOfficeEntities);

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

    @Override
    public Page<SolutionEntity> findAllSolution(Map<String, String> params, HttpSession session) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String title = params.get("title");
        String number = params.get("number");

        SolutionEntity solutionEntity = new SolutionEntity();
        if (StringUtils.isNotBlank(title)) {
            solutionEntity.setTitle(title);
        }
        if (StringUtils.isNotBlank(number)) {
            solutionEntity.setNumber(number);
        }
        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        return solutionRepository.findAll(Example.of(solutionEntity), pageRequest);
    }

    @Override
    public SolutionEntity findSolution(Integer id, HttpSession session) {
        return solutionRepository.findById(id);
    }
}
