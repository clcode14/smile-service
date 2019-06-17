package org.flightythought.smile.admin.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.AppUserInfo;
import org.flightythought.smile.admin.database.entity.UserEntity;
import org.flightythought.smile.admin.database.repository.UserRepository;
import org.flightythought.smile.admin.dto.AppUserQueryDTO;
import org.flightythought.smile.admin.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<AppUserInfo> findUsers(AppUserQueryDTO condition) {
        Pageable pageable = PageRequest.of(condition.getPageNumber()-1, condition.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

        Specification<UserEntity> sp = new Specification<UserEntity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotEmpty(condition.getMobile())) {
                    predicate.getExpressions().add(cb.equal(root.get("mobile"), condition.getMobile()));
                }
                if (StringUtils.isNotEmpty(condition.getNickName())) {
                    predicate.getExpressions().add(cb.equal(root.get("nickName"), condition.getNickName()));
                }
                if (StringUtils.isNotEmpty(condition.getCreateTimeStart())) {
                    String createTimeStart = condition.getCreateTimeStart() + " 00:00:00";
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("createTime"), LocalDateTime.parse(createTimeStart,df)));
                }
                if (StringUtils.isNotEmpty(condition.getCreateTimeEnd())) {
                    String createTimeEnd = condition.getCreateTimeEnd() + " 23:59:59";
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("createTime"), LocalDateTime.parse(createTimeEnd,df)));
                }
                return predicate;
            }
        };
        Page<UserEntity> pageList = userRepository.findAll(sp, pageable);
        List<UserEntity> list = pageList.getContent();
        List<AppUserInfo> appUserInfos = list.stream().map(e -> {
            AppUserInfo appUserInfo = new AppUserInfo();
            appUserInfo.setMobile(e.getMobile());
            appUserInfo.setNickName(e.getNickName());
            if(e.getRecommenderId()!=null) {
                UserEntity recommender = userRepository.findById(e.getRecommenderId()).orElse(null);
                appUserInfo.setRecommenderNickName(recommender == null ? "" : recommender.getNickName());
            }else {
                appUserInfo.setRecommenderNickName("");
            }
            appUserInfo.setCreateTime(e.getCreateTime());
            return appUserInfo;
        }).collect(Collectors.toList());
        PageImpl<AppUserInfo> result = new PageImpl<>(appUserInfos, pageable, pageList.getTotalElements());
        return result;
    }

}
