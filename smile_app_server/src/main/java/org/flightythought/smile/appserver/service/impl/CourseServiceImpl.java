package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.CourseSimple;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.appserver.dto.CourseQueryDTO;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public Page<CourseSimple> getCourseSimples(CourseQueryDTO courseQueryDTO) {
        Page<CourseRegistrationEntity> courseRegistrationEntities = getCourses(courseQueryDTO);
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
            courseSimples.add(courseSimple);
        });

        PageImpl<CourseSimple> result = new PageImpl<>(courseSimples, courseRegistrationEntities.getPageable(), courseRegistrationEntities.getTotalElements());
        return result;
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
}
