package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.CourseImageRepository;
import org.flightythought.smile.admin.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.CourseRegistrationDTO;
import org.flightythought.smile.admin.dto.ImageDTO;
import org.flightythought.smile.admin.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private CourseImageRepository courseImageRepository;

    @Value("${image-url}")
    private String imageRequest;
    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Override
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
                coverImage = new ImageInfo();
                String imageName = imagesEntity.getFileName();
                String url = domainPort + contentPath + imageRequest + imagesEntity.getPath();
                coverImage.setUrl(url.replace("\\", "/"));
                coverImage.setName(imageName);
                coverImage.setId(coverImage.getId());
                coverImage.setSize(coverImage.getSize());
                coverImages.add(coverImage);
            }
            courseInfo.setCoverImage(coverImages);
            // 展示图
            List<ImagesEntity> courseImageEntities = courseRegistrationEntity.getCourseImages();
            List<ImageInfo> courseImages = new ArrayList<>();
            if (courseImageEntities != null && courseImageEntities.size() > 0) {
                courseImageEntities.forEach(courseImageEntity -> {
                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setName(courseImageEntity.getFileName());
                    imageInfo.setId(courseImageEntity.getId());
                    imageInfo.setSize(courseImageEntity.getSize());
                    String imageUrl = domainPort + contentPath + imageRequest + courseImageEntity.getPath();
                    imageInfo.setUrl(imageUrl.replace("\\", "/"));
                    courseImages.add(imageInfo);
                });
            }
            courseInfo.setCourseImages(courseImages);

            courseInfos.add(courseInfo);
        });
        PageImpl<CourseInfo> result = new PageImpl<>(courseInfos, pageable, courseRegistrationEntities.getTotalElements());
        return result;
    }
}
