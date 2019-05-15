package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.HomeBanner;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.CourseBannerRepository;
import org.flightythought.smile.admin.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.admin.database.repository.HomeBannerRepository;
import org.flightythought.smile.admin.dto.HomeBannerDTO;
import org.flightythought.smile.admin.service.CommonService;
import org.flightythought.smile.admin.service.CourseRegistrationService;
import org.flightythought.smile.admin.service.HomeBannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private CommonService commonService;

    @Override
    public List<HomeBanner> findAll() {
        String domainPort = platformUtils.getDomainPort();
        List<HomeBanner> homeBanners = homeBannerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingInt(value -> value.getSort() == null ? 0 : value.getSort()))
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
        HomeBannerEntity exampleEntity = new HomeBannerEntity();
        exampleEntity.setId(id);
        homeBannerRepository.findOne(Example.of(exampleEntity))
                .ifPresent(homeBanner -> {
                    String domainPort = platformUtils.getDomainPort();
                    ImagesEntity imagesEntity = homeBanner.getImagesEntity();
                    ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                    String imageUrlByPath = imageInfo.getUrl();
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
        HomeBannerEntity homeBannerEntity = homeBannerRepository.findById(homebannerDTO.getId());
        homeBannerEntity.setSort(homebannerDTO.getSort());
        homeBannerEntity.setLink(homebannerDTO.getLink());
        homeBannerEntity.setTitle(homebannerDTO.getTitle());
        if (!homebannerDTO.getImageId().equals(homeBannerEntity.getImageId())) {
            Integer imageId = homeBannerEntity.getImageId();
            if (imageId != null) {
                // 删除原始图片
                commonService.deleteImage(imageId);
            }
            homeBannerEntity.setImageId(homebannerDTO.getImageId());
        }
        homeBannerEntity.setUpdateUserName(sysUserEntity.getUserName());
        homeBannerEntity.setId(homebannerDTO.getId());
        return homeBannerRepository.save(homeBannerEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        HomeBannerEntity homeBannerEntity = homeBannerRepository.findById(id);
        // 删除图片
        Integer imageId = homeBannerEntity.getImageId();
        if (imageId != null) {
            // 删除原始图片
            commonService.deleteImage(imageId);
        }
        homeBannerEntity = homeBannerRepository.findById(id);
        homeBannerRepository.delete(homeBannerEntity);
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
