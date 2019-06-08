package org.flightythought.smile.admin.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.CourseTypeInfo;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.CourseImageEntity;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.database.entity.CourseTypeEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysParameterEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.CourseImageRepository;
import org.flightythought.smile.admin.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.admin.database.repository.CourseTypeRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.CourseRegistrationDTO;
import org.flightythought.smile.admin.dto.ImageDTO;
import org.flightythought.smile.admin.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private CourseImageRepository courseImageRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public CourseRegistrationEntity addCourseRegistration(CourseRegistrationDTO courseRegistrationDTO, HttpSession session) {
        CourseRegistrationEntity courseRegistrationEntity = null;
        Integer courseId = courseRegistrationDTO.getCourseId();
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        if (courseId != null) {
            courseRegistrationEntity = courseRegistrationRepository.findByCourseId(courseId);
        }
        if (courseRegistrationEntity == null) {
            courseRegistrationEntity = new CourseRegistrationEntity();
            // 创建者
            courseRegistrationEntity.setCreateUserName(sysUserEntity.getLoginName());
        } else {
            // 删除课程ID所对应的图片
            List<CourseImageEntity> courseImageEntities = courseImageRepository.findByCourseId(courseId);
            courseImageRepository.deleteAll(courseImageEntities);
            courseRegistrationEntity.setUpdateUserName(sysUserEntity.getLoginName());
        }
        // 课程标题
        courseRegistrationEntity.setTitle(courseRegistrationDTO.getTitle());
        LocalDateTime startTime = LocalDateTime.parse(courseRegistrationDTO.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 开始时间
        courseRegistrationEntity.setStartTime(startTime);
        // 报名人数
        courseRegistrationEntity.setMembers(courseRegistrationDTO.getMembers());
        // 地址
        courseRegistrationEntity.setAddress(courseRegistrationDTO.getAddress());
        // 描述
        courseRegistrationEntity.setDescription(courseRegistrationDTO.getDescription());
        // 价格
        courseRegistrationEntity.setPrice(courseRegistrationDTO.getPrice());
        // 课程类型ID
        courseRegistrationEntity.setTypeId(courseRegistrationDTO.getTypeId());
        ImageDTO coverImage = courseRegistrationDTO.getCoverImage();
        if (coverImage != null) {
            // 封面图片
            courseRegistrationEntity.setCoverImageId(coverImage.getImageId());
        }
        // 保存课程
        CourseRegistrationEntity courseRegistration = courseRegistrationRepository.save(courseRegistrationEntity);
        // 保存课程图片
        List<ImageDTO> imageDTOS = courseRegistrationDTO.getCourseImages();
        if (imageDTOS != null && imageDTOS.size() > 0) {
            List<CourseImageEntity> courseImageEntities = new ArrayList<>();
            imageDTOS.forEach(imageDTO -> {
                CourseImageEntity courseImageEntity = new CourseImageEntity();
                courseImageEntity.setCourseId(courseRegistration.getCourseId());
                courseImageEntity.setCourseImageId(imageDTO.getImageId());
                courseImageEntities.add(courseImageEntity);
            });
            courseImageRepository.saveAll(courseImageEntities);
        }
        return courseRegistration;
    }

    @Override
    @Transactional
    public Page<CourseInfo> getCourseRegistration(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findAll(pageable);
        List<CourseInfo> courseInfos = new ArrayList<>();
        SysParameterEntity sysParameterEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = sysParameterEntity.getParameterValue();
        courseRegistrationEntities.forEach(courseRegistrationEntity -> {
            CourseInfo courseInfo = new CourseInfo();
            // 课程ID
            courseInfo.setCourseId(courseRegistrationEntity.getCourseId());
            // 标题
            courseInfo.setTitle(courseRegistrationEntity.getTitle());
            // 开始时间
            courseInfo.setStartTime(courseRegistrationEntity.getStartTime());
            // 报名人数
            courseInfo.setMembers(courseRegistrationEntity.getMembers());
            // 价格
            courseInfo.setPrice(courseRegistrationEntity.getPrice());
            // 活动地址
            courseInfo.setAddress(courseRegistrationEntity.getAddress());
            // 详情描述
            courseInfo.setDescription(courseRegistrationEntity.getDescription());
            // 封面图
            ImageInfo coverImage = null;
            ImagesEntity imagesEntity = courseRegistrationEntity.getCoverImage();
            List<ImageInfo> coverImages = new ArrayList<>();
            if (imagesEntity != null) {
                coverImage = platformUtils.getImageInfo(imagesEntity, domainPort);
                coverImages.add(coverImage);
            }
            courseInfo.setCoverImage(coverImages);
            // 展示图
            List<ImagesEntity> courseImageEntities = courseRegistrationEntity.getCourseImages();
            List<ImageInfo> courseImages = new ArrayList<>();
            if (courseImageEntities != null && courseImageEntities.size() > 0) {
                courseImageEntities.forEach(courseImageEntity -> {
                    ImageInfo imageInfo = platformUtils.getImageInfo(courseImageEntity, domainPort);
                    courseImages.add(imageInfo);
                });
            }
            courseInfo.setCourseImages(courseImages);

            // 课程类型
            CourseTypeEntity courseTypeEntity = courseRegistrationEntity.getCourseTypeEntity();
            if (courseTypeEntity != null) {
                CourseTypeInfo courseTypeInfo = new CourseTypeInfo();
                // 类型名称
                courseTypeInfo.setCourseTypeName(courseTypeEntity.getCourseTypeName());
                // 类型ID
                courseTypeInfo.setTypeId(courseTypeEntity.getId());
                courseInfo.setCourseType(courseTypeInfo);
            }
            courseInfos.add(courseInfo);
        });
        return new PageImpl<>(courseInfos, pageable, courseRegistrationEntities.getTotalElements());
    }

    @Override
    @Transactional
    public CourseInfo getCourseRegistrationDetail(Integer courseId) {
        CourseRegistrationEntity courseRegistrationEntity = courseRegistrationRepository.findByCourseId(courseId);
        return getCourseInfo(courseRegistrationEntity);
    }

    public CourseInfo getCourseInfo(CourseRegistrationEntity courseRegistrationEntity) {
        String domainPort = platformUtils.getDomainPort();

        CourseInfo courseInfo = new CourseInfo();
        // 课程ID
        courseInfo.setCourseId(courseRegistrationEntity.getCourseId());
        // 标题
        courseInfo.setTitle(courseRegistrationEntity.getTitle());
        // 开始时间
        courseInfo.setStartTime(courseRegistrationEntity.getStartTime());
        // 报名人数
        courseInfo.setMembers(courseRegistrationEntity.getMembers());
        // 价格
        courseInfo.setPrice(courseRegistrationEntity.getPrice());
        // 活动地址
        courseInfo.setAddress(courseRegistrationEntity.getAddress());
        // 详情描述
        courseInfo.setDescription(courseRegistrationEntity.getDescription());
        // 封面图
        ImageInfo coverImage;
        ImagesEntity imagesEntity = courseRegistrationEntity.getCoverImage();
        List<ImageInfo> coverImages = new ArrayList<>();
        if (imagesEntity != null) {
            coverImage = platformUtils.getImageInfo(imagesEntity, domainPort);
            coverImages.add(coverImage);
        }
        courseInfo.setCoverImage(coverImages);
        // 展示图
        List<ImagesEntity> courseImageEntities = courseRegistrationEntity.getCourseImages();
        List<ImageInfo> courseImages = new ArrayList<>();
        if (courseImageEntities != null && courseImageEntities.size() > 0) {
            courseImageEntities.forEach(courseImageEntity -> {
                ImageInfo imageInfo = platformUtils.getImageInfo(courseImageEntity, domainPort);
                courseImages.add(imageInfo);
            });
        }
        courseInfo.setCourseImages(courseImages);
        // 课程类型
        CourseTypeEntity courseTypeEntity = courseRegistrationEntity.getCourseTypeEntity();
        if (courseTypeEntity != null) {
            CourseTypeInfo courseTypeInfo = new CourseTypeInfo();
            // 类型名称
            courseTypeInfo.setCourseTypeName(courseTypeEntity.getCourseTypeName());
            // 类型ID
            courseTypeInfo.setTypeId(courseTypeEntity.getId());
            courseInfo.setCourseType(courseTypeInfo);
        }
        return courseInfo;
    }

    @Override
    @Transactional
    public void deleteCourseRegistration(Integer courseId) {
        courseRegistrationRepository.deleteById(courseId);
    }

    @Override
    public List<SelectItemOption> getCourseType() {
        List<CourseTypeEntity> courseTypeEntities = courseTypeRepository.findAll();
        List<SelectItemOption> result = new ArrayList<>();
        courseTypeEntities.forEach(courseTypeEntity -> {
            SelectItemOption selectItemOption = new SelectItemOption();
            selectItemOption.setKey(courseTypeEntity.getId() + "");
            selectItemOption.setValue(courseTypeEntity.getCourseTypeName());
            result.add(selectItemOption);
        });
        return result;
    }
}
