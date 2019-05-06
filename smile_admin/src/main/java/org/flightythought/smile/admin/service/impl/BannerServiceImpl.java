package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.CourseTypeInfo;
import org.flightythought.smile.admin.bean.HomeBanner;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.CourseBannerRepository;
import org.flightythought.smile.admin.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.admin.database.repository.HomeBannerRepository;
import org.flightythought.smile.admin.dto.HomeBannerDTO;
import org.flightythought.smile.admin.service.CourseRegistrationService;
import org.flightythought.smile.admin.service.HomeBannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements HomeBannerService {
    @Autowired
    private HomeBannerRepository homeBannerRepository;
    @Autowired
    private CourseBannerRepository courseBannerRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @Override
    public List<HomeBanner> findAll() {
        String domainPort = platformUtils.getDomainPort();
        List<HomeBanner> homeBanners = homeBannerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingInt(HomeBannerEntity::getSort))
                .map(homeBannerEntity -> {
                    HomeBanner homeBanner = new HomeBanner();
                    BeanUtils.copyProperties(homeBannerEntity, homeBanner);
                    ImagesEntity imagesEntity = homeBannerEntity.getImagesEntity();
                    ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                    homeBanner.setImage(imageInfo);
                    return homeBanner;
                }).collect(Collectors.toList());
        return homeBanners;
    }

    @Override
    public HomeBannerEntity findOne(Integer id) {
        AtomicReference<HomeBannerEntity> homeBannerEntity = new AtomicReference<>();
        homeBannerRepository.findById(id)
                .ifPresent(homeBanner -> {
                    String domainPort = platformUtils.getDomainPort();
                    ImagesEntity imagesEntity = homeBanner.getImagesEntity();
                    String imageUrlByPath = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
                    imagesEntity.setPath(imageUrlByPath);
                    homeBanner.setImagesEntity(imagesEntity);
                    homeBannerEntity.set(homeBanner);
                });
        return homeBannerEntity.get();
    }

    @Override
    public HomeBannerEntity create(HomeBannerDTO homebannerDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);

        HomeBannerEntity homeBannerEntity = new HomeBannerEntity();
        homeBannerEntity.setSort(homebannerDTO.getSort());
        homeBannerEntity.setLink(homebannerDTO.getLink());
        homeBannerEntity.setTitle(homebannerDTO.getTitle());
        homeBannerEntity.setImageId(homebannerDTO.getImageId());
        homeBannerEntity.setCreateUserName(sysUserEntity.getUserName());
        return homeBannerRepository.save(homeBannerEntity);
    }

    @Override
    public HomeBannerEntity modify(HomeBannerDTO homebannerDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);

        HomeBannerEntity homeBannerEntity = new HomeBannerEntity();
        homeBannerEntity.setSort(homebannerDTO.getSort());
        homeBannerEntity.setLink(homebannerDTO.getLink());
        homeBannerEntity.setTitle(homebannerDTO.getTitle());
        homeBannerEntity.setImageId(homebannerDTO.getImageId());
        homeBannerEntity.setUpdateUserName(sysUserEntity.getUserName());
        homeBannerEntity.setId(homebannerDTO.getId());
        return homeBannerRepository.save(homeBannerEntity);
    }

    @Override
    public void deleteById(Integer id) {
        homeBannerRepository.deleteById(id);
    }

    @Override
    public void addCourseBanner(List<Integer> courseIds) {
        // 获取当前登录用户
        SysUserEntity sysUserEntity = platformUtils.getCurrentLoginUser();
        List<CourseBannerEntity> courseBannerEntities = courseIds.stream().map(integer -> {
            CourseBannerEntity courseBannerEntity = new CourseBannerEntity();
            courseBannerEntity.setStatus(true);
            courseBannerEntity.setCourseId(integer);
            courseBannerEntity.setCreateUserName(sysUserEntity.getLoginName());
            return courseBannerEntity;
        }).collect(Collectors.toList());
        // 保存课程Banner信息
        courseBannerRepository.saveAll(courseBannerEntities);
    }

    @Override
    public List<CourseInfo> getBannerOfCourseInfo() {
        // 获取被设置成课程Banner图的课程
        List<CourseBannerEntity> courseBannerEntities = courseBannerRepository.findAll();
        List<Integer> courseIds = courseBannerEntities.stream().map(CourseBannerEntity::getCourseId).collect(Collectors.toList());
        // 获取课程
        List<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findByCourseIdIn(courseIds);
        List<CourseInfo> courseInfos = courseRegistrationEntities.stream()
                .map(courseRegistrationEntity -> courseRegistrationService.getCourseInfo(courseRegistrationEntity)).collect(Collectors.toList());
        return courseInfos;
    }

    @Override
    public Page<CourseInfo> getCourseRegistration(int pageNumber, int pageSize) {
        List<CourseBannerEntity> courseBannerEntities = courseBannerRepository.findAll();
        List<Integer> courseIds = courseBannerEntities.stream().map(CourseBannerEntity::getCourseId).collect(Collectors.toList());
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findByCourseIdNotIn(courseIds, pageable);
        List<CourseInfo> courseInfos = courseRegistrationEntities.stream().map(courseRegistrationEntity -> courseRegistrationService.getCourseInfo(courseRegistrationEntity)).collect(Collectors.toList());
        return new PageImpl<>(courseInfos, pageable, courseRegistrationEntities.getTotalElements());
    }

    @Override
    public void deleteCourseBanner(List<Integer> courseIds) {
        List<CourseBannerEntity> courseBannerEntities = courseBannerRepository.findByCourseIdIn(courseIds);
        if (courseBannerEntities != null && courseBannerEntities.size() > 0) {
            courseBannerRepository.deleteAll(courseBannerEntities);
        }
    }
}
