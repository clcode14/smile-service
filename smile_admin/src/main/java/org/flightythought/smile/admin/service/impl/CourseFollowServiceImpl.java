package org.flightythought.smile.admin.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.flightythought.smile.admin.bean.CourseFollowInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.ReportExportUtil;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.database.entity.UserFollowCourseEntity;
import org.flightythought.smile.admin.database.repository.CourseRegistrationRepository;
import org.flightythought.smile.admin.database.repository.UserFollowCourseRepository;
import org.flightythought.smile.admin.dto.CourseFollowQueryDTO;
import org.flightythought.smile.admin.service.CourseFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CourseFollowServiceImpl implements CourseFollowService {

    @Autowired
    private UserFollowCourseRepository userFollowCourseRepository;
    
    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public Page<CourseFollowInfo> getFollowPage(CourseFollowQueryDTO condition) {
        Pageable pageable = PageRequest.of(condition.getPageNumber() - 1, condition.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

        Specification<UserFollowCourseEntity> sp = new Specification<UserFollowCourseEntity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<UserFollowCourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotEmpty(condition.getPhone())) {
                    predicate.getExpressions().add(cb.equal(root.get("phone"), condition.getPhone()));
                }
                if (condition.getCourseId() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("courseId"), condition.getCourseId()));
                }
                return predicate;
            }
        };
        Page<UserFollowCourseEntity> pageList = userFollowCourseRepository.findAll(sp, pageable);
        List<UserFollowCourseEntity> list = pageList.getContent();
        List<CourseFollowInfo> courseFollowInfos = list.stream().map(e -> {
            CourseFollowInfo courseFollowInfo = new CourseFollowInfo();
            courseFollowInfo.setName(e.getName());
            courseFollowInfo.setPhone(e.getPhone());
            courseFollowInfo.setCreateTime(e.getCreateTime());
            courseFollowInfo.setPrice(e.getCourseRegistrationEntity().getPrice());
            courseFollowInfo.setTitle(e.getCourseRegistrationEntity().getTitle());
            return courseFollowInfo;
        }).collect(Collectors.toList());
        PageImpl<CourseFollowInfo> result = new PageImpl<>(courseFollowInfos, pageable, pageList.getTotalElements());
        return result;
    }

    @Override
    public List<SelectItemOption> getCourseRegistration() {
        List<CourseRegistrationEntity> entities = courseRegistrationRepository.findAll();
        List<SelectItemOption> result = new ArrayList<>();
        entities.forEach(courseTypeEntity -> {
            SelectItemOption selectItemOption = new SelectItemOption();
            selectItemOption.setKey(courseTypeEntity.getCourseId() + "");
            selectItemOption.setValue(courseTypeEntity.getTitle());
            result.add(selectItemOption);
        });
        return result;
    }

    @Override
    public Workbook getFollowWorkbook(String phone, Integer courseId) {
        Specification<UserFollowCourseEntity> sp = new Specification<UserFollowCourseEntity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<UserFollowCourseEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotEmpty(phone)) {
                    predicate.getExpressions().add(cb.equal(root.get("phone"), phone));
                }
                if (courseId != null) {
                    predicate.getExpressions().add(cb.equal(root.get("courseId"), courseId));
                }
                return predicate;
            }
        };
        List<UserFollowCourseEntity> list = userFollowCourseRepository.findAll(sp);
        String[] headers = { "姓名", "手机号", "报名课程", "报名费用", "报名时间" };
        List<List<String>> dataList = list.stream().map(e -> {
            List<String> data = new ArrayList<>();
            data.add(e.getName());
            data.add(e.getPhone());
            data.add(e.getCourseRegistrationEntity().getTitle());
            data.add(e.getCourseRegistrationEntity().getPrice().toString());
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            data.add(df.format(e.getCreateTime()));
            return data;
        }).collect(Collectors.toList());
        SXSSFWorkbook workbook = ReportExportUtil.exportExcelCommon("课程报名列表", Arrays.asList(headers), dataList);
        return workbook;
    }

}
