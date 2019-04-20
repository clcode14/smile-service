package org.flightythought.smile.appserver.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.flightythought.smile.appserver.bean.CourseSimple;
import org.flightythought.smile.appserver.bean.SelectItemOption;
import org.flightythought.smile.appserver.bean.SmsCodeData;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.AesUtils;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.config.properties.AppProperties;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.CourseBannerRepository;
import org.flightythought.smile.appserver.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.appserver.database.repository.CourseTypeRepository;
import org.flightythought.smile.appserver.database.repository.UserFollowCourseRepository;
import org.flightythought.smile.appserver.dto.ApplyCourseDTO;
import org.flightythought.smile.appserver.dto.CourseInfoQueryDTO;
import org.flightythought.smile.appserver.dto.CourseQueryDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.CourseService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private UserFollowCourseRepository userFollowCourseRepository;
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private CourseBannerRepository courseBannerRepository;

    @Override
    @Transactional
    public Page<CourseSimple> getCourseSimples(CourseQueryDTO courseQueryDTO) {
        Page<CourseRegistrationEntity> courseRegistrationEntities = getCourses(courseQueryDTO);
        return getCourseSimples(courseRegistrationEntities);
    }

    @Override
    @Transactional
    public Page<CourseSimple> getCourseSimples(Page<CourseRegistrationEntity> courseRegistrationEntities) {
        List<CourseSimple> courseSimples = new ArrayList<>();
        String domainPort = platformUtils.getDomainPort();
        courseRegistrationEntities.forEach(courseRegistrationEntity -> {
            CourseSimple courseSimple = new CourseSimple();
            // 课程图片
            List<String> courseImages = new ArrayList<>();
            List<ImagesEntity> imagesEntities = courseRegistrationEntity.getCourseImages();
            imagesEntities.forEach(imagesEntity -> {
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                courseImages.add(imageUrl);
            });
            courseSimple.setCourseImages(courseImages);
            // 封面图片
            ImagesEntity imagesEntity = courseRegistrationEntity.getCoverImage();
            if (imagesEntity != null) {
                String coverImageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                courseSimple.setCoverImageUrl(coverImageUrl);
            }
            // 课程ID
            courseSimple.setCourseId(courseRegistrationEntity.getCourseId());
            // 课程标题
            courseSimple.setTitle(courseRegistrationEntity.getTitle());
            // 开始时间
            courseSimple.setStartTime(courseRegistrationEntity.getStartTime());
            // 价格
            courseSimple.setPrice(courseRegistrationEntity.getPrice());
            // 可报名人数
            courseSimple.setMembers(courseRegistrationEntity.getMembers());
            // 已报名人数
            courseSimple.setApplyCount(courseRegistrationEntity.getApplyCount());
            // 活动地址
            courseSimple.setAddress(courseRegistrationEntity.getAddress());
            // 详情描述
            courseSimple.setDescription(courseRegistrationEntity.getDescription());
            // 课程类型ID
            courseSimple.setTypeId(courseRegistrationEntity.getTypeId());
            courseSimples.add(courseSimple);
        });

        PageImpl<CourseSimple> result = new PageImpl<>(courseSimples, courseRegistrationEntities.getPageable(), courseRegistrationEntities.getTotalElements());
        return result;
    }

    @Override
    public UserFollowCourseEntity applyCourse(ApplyCourseDTO applyCourseDTO) throws FlightyThoughtException {
        // 判断输入的课程ID是否有效
        Integer courseId = applyCourseDTO.getCourseId();
        CourseRegistrationEntity courseRegistrationEntity = courseRegistrationRepository.findByCourseId(courseId);
        if (courseRegistrationEntity == null) {
            throw new FlightyThoughtException("输入的课程ID无效!");
        }
        // 判断用户是否输入姓名
        String name = applyCourseDTO.getName();
        if (StringUtils.isBlank(name)) {
            throw new FlightyThoughtException("请输入姓名");
        }
        // 判断用户是否输入手机号码
        String phone = applyCourseDTO.getPhone();
        if (StringUtils.isBlank(phone)) {
            throw new FlightyThoughtException("请输入手机号码");
        }
        // 短信验证码
        String vCode = applyCourseDTO.getCode();
        if (StringUtils.isBlank(vCode)) {
            throw new FlightyThoughtException("请输入短信验证码");
        }
        // 判断Token有无输入
        String token = applyCourseDTO.getToken();
        if (StringUtils.isBlank(token)) {
            throw new FlightyThoughtException("请传递有效Token!");
        }
        // 判断短信验证码是否正确
        String params;
        try {
            //解密短信验证码
            params = AesUtils.aesDecryptHexString(token, appProperties.getCodeKey());
        } catch (Exception e) {
            throw new FlightyThoughtException("短信加密Token输入有误");
        }
        SmsCodeData smsCodeData = JSON.parseObject(params, SmsCodeData.class);
        // 短信验证码不正确
        if (!vCode.equals(smsCodeData.getVCode())) {
            throw new FlightyThoughtException("短信验证码不正确");
        }
        // 手机号不符
        if (!phone.equals(smsCodeData.getPhone())) {
            throw new FlightyThoughtException("手机号不符");
        }
        // 验证码失效
        Long now = System.currentTimeMillis();
        if ((now - smsCodeData.getTime()) > 10 * 60 * 1000) {
            throw new FlightyThoughtException("短信验证码失效，请重新获取");
        }
        // 保存预约课程对象
        UserFollowCourseEntity userFollowCourseEntity = new UserFollowCourseEntity();
        // 获取登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        userFollowCourseEntity.setUserId(userEntity.getId());
        // 课程ID
        userFollowCourseEntity.setCourseId(courseId);
        // 预约报名姓名
        userFollowCourseEntity.setName(name);
        // 手机号码
        userFollowCourseEntity.setPhone(phone);
        // 创建者
        userFollowCourseEntity.setCreateUserName(userEntity.getId() + "");
        return userFollowCourseRepository.save(userFollowCourseEntity);
    }

    @Override
    @Transactional
    public Page<CourseSimple> getUserCourses(PageFilterDTO pageFilterDTO) {
        Integer pageNumber = pageFilterDTO.getPageNumber();
        Integer pageSize = pageFilterDTO.getPageSize();
        PageRequest pageRequest;
        List<UserFollowCourseEntity> userFollowCourseEntities;
        long total;
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            userFollowCourseEntities = userFollowCourseRepository.findAll();
            pageRequest = PageRequest.of(0, userFollowCourseEntities.size());
            total = (long) userFollowCourseEntities.size();
        } else {
            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<UserFollowCourseEntity> userFollowCourseEntityPage = userFollowCourseRepository.findAll(pageRequest);
            userFollowCourseEntities = userFollowCourseEntityPage.getContent();
            total = userFollowCourseEntityPage.getTotalElements();
        }
        List<Integer> courseIds = new ArrayList<>();
        userFollowCourseEntities.forEach(userFollowCourseEntity -> courseIds.add(userFollowCourseEntity.getCourseId()));
        List<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findByCourseIdIn(courseIds);
        List<CourseSimple> courseSimples = getCourseSimple(courseRegistrationEntities);
        return new PageImpl<>(courseSimples, pageRequest, total);
    }

    @Override
    public List<CourseSimple> getCourseSimple(List<CourseRegistrationEntity> courseRegistrationEntities) {
        List<CourseSimple> courseSimples = new ArrayList<>();
        String domainPort = platformUtils.getDomainPort();
        courseRegistrationEntities.forEach(courseRegistrationEntity -> {
            // 课程ID
            CourseSimple courseSimple = new CourseSimple();
            // 课程ID
            courseSimple.setCourseId(courseRegistrationEntity.getCourseId());
            // 课程标题
            courseSimple.setTitle(courseRegistrationEntity.getTitle());
            // 开始时间
            courseSimple.setStartTime(courseRegistrationEntity.getStartTime());
            // 价格
            courseSimple.setPrice(courseRegistrationEntity.getPrice());
            // 可报名人数
            courseSimple.setMembers(courseRegistrationEntity.getMembers());
            // 活动地址
            courseSimple.setAddress(courseRegistrationEntity.getAddress());
            // 封面图片
            ImagesEntity imagesEntity = courseRegistrationEntity.getCoverImage();
            if (imagesEntity != null) {
                String url = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                courseSimple.setCoverImageUrl(url);
            }
            // 课程图片
            List<String> courseImages = new ArrayList<>();
            List<ImagesEntity> imagesEntities = courseRegistrationEntity.getCourseImages();
            imagesEntities.forEach(imagesEntity1 -> {
                String url = platformUtils.getImageUrlByPath(imagesEntity1.getPath(), domainPort);
                courseImages.add(url);
            });
            courseSimple.setCourseImages(courseImages);
            // 详情描述
            courseSimple.setDescription(courseRegistrationEntity.getDescription());
            courseSimples.add(courseSimple);
        });
        return courseSimples;
    }

    @Override
    public Page<SelectItemOption> getCourseType(PageFilterDTO pageFilterDTO) {
        List<SelectItemOption> result = new ArrayList<>();
        // 获取课程类型
        Integer pageSize = pageFilterDTO.getPageSize();
        Integer pageNumber = pageFilterDTO.getPageNumber();
        Pageable pageable;
        List<CourseTypeEntity> courseTypeEntities;
        Long total;
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            // 获取全部课程分类
            courseTypeEntities = courseTypeRepository.findAll();
            pageable = PageRequest.of(0, courseTypeEntities.size() == 0 ? 1 : courseTypeEntities.size());
            total = (long) courseTypeEntities.size();
        } else {
            pageable = PageRequest.of(pageNumber - 1, pageSize);
            Page<CourseTypeEntity> courseTypeEntityPage = courseTypeRepository.findAll(pageable);
            courseTypeEntities = courseTypeEntityPage.getContent();
            total = courseTypeEntityPage.getTotalElements();
        }
        if (courseTypeEntities.size() > 0) {
            courseTypeEntities.forEach(courseTypeEntity -> {
                SelectItemOption selectItemOption = new SelectItemOption();
                selectItemOption.setKey(courseTypeEntity.getId() + "");
                selectItemOption.setValue(courseTypeEntity.getCourseTypeName());
                result.add(selectItemOption);
            });
        }
        SelectItemOption selectItemOption = new SelectItemOption();
        selectItemOption.setKey("0");
        selectItemOption.setValue("其他课程");
        result.add(selectItemOption);
        return new PageImpl<>(result, pageable, total + 1);
    }

    @Override
    @Transactional
    public List<CourseSimple> getCourseBanner() {
        List<CourseBannerEntity> courseBannerEntities = courseBannerRepository.findByStatus(true);
        List<CourseRegistrationEntity> courseRegistrationEntities = new ArrayList<>();
        courseBannerEntities.forEach(courseBannerEntity -> courseRegistrationEntities.add(courseBannerEntity.getCourseRegistrationEntity()));
        return this.getCourseSimple(courseRegistrationEntities);
    }

    @Override
    @Transactional
    public Page<CourseRegistrationEntity> getCourses(CourseQueryDTO courseQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) as total FROM (";
        String sql = "SELECT\n" +
                "  cr.`course_id`\n" +
                "FROM\n" +
                "  `tb_course_registration` cr\n" +
                "  LEFT JOIN `tb_solution_course` sc\n" +
                "    ON cr.`course_id` = sc.`course_id` WHERE 1 = 1";
        // 解决方案ID
        Integer solutionId = courseQueryDTO.getSolutionId();
        if (solutionId != null) {
            sql += " AND sc.`solution_id` = " + solutionId;
        }
        // 课程ID
        Integer diseaseReasonId = courseQueryDTO.getCourseId();
        if (diseaseReasonId != null) {
            sql += " AND cr.`course_id` = " + diseaseReasonId;
        }
        totalSql += sql + ") T";
        // 获取ToTal总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("未查询到相关课程");
        }
        // 是否存在分页查询
        Integer pageNumber = courseQueryDTO.getPageNumber();
        Integer pageSize = courseQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql += " LIMIT " + (pageNumber - 1) * pageSize + "," + pageSize;
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> courseIds = entityManager.createNativeQuery(sql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("course_id", IntegerType.INSTANCE)
                .list();
        // 获取全部SolutionEntities
        List<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findByCourseIdIn(courseIds);
        PageImpl<CourseRegistrationEntity> result = new PageImpl<>(courseRegistrationEntities, pageable, total);
        return result;
    }

    @Override
    @Transactional
    public Page<CourseSimple> getCoursesInfo(CourseInfoQueryDTO courseInfoQueryDTO) {
        Page<CourseRegistrationEntity> courseRegistrationEntities = getCourses(courseInfoQueryDTO);
        return getCourseSimples(courseRegistrationEntities);
    }

    @Override
    @Transactional
    public Page<CourseRegistrationEntity> getCourses(CourseInfoQueryDTO courseInfoQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) as total FROM (";
        String sql = "SELECT\n" +
                "  cr.`course_id`\n" +
                "FROM\n" +
                "  `tb_course_registration` cr\n" +
                "WHERE 1 = 1 ";
        // 开始时间
        if (courseInfoQueryDTO.getStartTime() != null) {
            String startTime = courseInfoQueryDTO.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            sql += " AND cr.`start_time` >= '" + startTime + "'";
        }
        // 结束时间
        if (courseInfoQueryDTO.getEndTime() != null) {
            String endTime = courseInfoQueryDTO.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            sql += " AND cr.`start_time` <= '" + endTime + "'";
        }
        // 课程ID
        if (courseInfoQueryDTO.getCourseId() != null) {
            sql += " AND cr.`course_id` = " + courseInfoQueryDTO.getCourseId();
        }
        // 课程类型ID
        if (courseInfoQueryDTO.getTypeId() != null) {
            if (courseInfoQueryDTO.getTypeId() == 0) {
                sql += " AND cr.`type_id` IS NULL ";
            } else {
                sql += " AND cr.`type_id` = " + courseInfoQueryDTO.getTypeId();
            }
        }
        totalSql += sql + ") T";
        // 获取ToTal总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("未查询到相关课程");
        }
        // 是否存在分页查询
        Integer pageNumber = courseInfoQueryDTO.getPageNumber();
        Integer pageSize = courseInfoQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql += " LIMIT " + (pageNumber - 1) * pageSize + "," + pageSize;
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> courseIds = entityManager.createNativeQuery(sql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("course_id", IntegerType.INSTANCE)
                .list();
        List<CourseRegistrationEntity> courseRegistrationEntities = courseRegistrationRepository.findByCourseIdIn(courseIds);
        PageImpl<CourseRegistrationEntity> result = new PageImpl<>(courseRegistrationEntities, pageable, total);
        return result;
    }
}
