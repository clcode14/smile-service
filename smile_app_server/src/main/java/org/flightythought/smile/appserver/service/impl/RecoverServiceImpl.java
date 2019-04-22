package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.RecoverCaseSimple;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.RecoverCaseEntity;
import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.RecoverCaseRepository;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseByIdQueryDTO;
import org.flightythought.smile.appserver.service.RecoverService;
import org.flightythought.smile.appserver.service.UserService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
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
public class RecoverServiceImpl implements RecoverService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RecoverCaseRepository recoverCaseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public Page<RecoverCaseSimple> getRecoverCase(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO) {
        Page<RecoverCaseEntity> recoverCaseEntities = getRecoverCaseEntities(healthOrDiseaseByIdQueryDTO);
        String domainPort = platformUtils.getDomainPort();
        List<RecoverCaseSimple> result = new ArrayList<>();
        recoverCaseEntities.forEach(recoverCaseEntity -> {
            RecoverCaseSimple recoverCaseSimple = new RecoverCaseSimple();
            // 主键ID
            recoverCaseSimple.setRecoverId(recoverCaseEntity.getId());
            // 养生旅程ID
            recoverCaseSimple.setJourneyId(recoverCaseEntity.getJourneyId());
            // 解决方案ID
            recoverCaseSimple.setSolutionId(recoverCaseEntity.getSolutionId());
            // 康复案例标题
            recoverCaseSimple.setTitle(recoverCaseEntity.getTitle());
            // 案例开始时间
            recoverCaseSimple.setCaseStartTime(recoverCaseEntity.getCaseStartTime());
            // 案例结束时间
            recoverCaseSimple.setCaseEndTime(recoverCaseEntity.getCaseEndTime());
            // 封面图
            recoverCaseSimple.setCoverImage(platformUtils.getImageInfo(recoverCaseEntity.getCoverImage(), domainPort));
            // 用户信息
            UserEntity userEntity = recoverCaseEntity.getUser();
            if (userEntity != null && userEntity.getId() != null) {
                UserInfo userInfo = userService.getUserInfo(userEntity);
                recoverCaseSimple.setUserInfo(userInfo);
            }
            // 阅读数
            recoverCaseSimple.setReadNum(recoverCaseEntity.getReadNum());
            result.add(recoverCaseSimple);
        });
        return new PageImpl<>(result, recoverCaseEntities.getPageable(), recoverCaseEntities.getTotalElements());
    }

    @Override
    @Transactional
    public Page<RecoverCaseEntity> getRecoverCaseEntities(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        StringBuilder sql = new StringBuilder("SELECT DISTINCT a.`id` AS recoverId FROM `vw_disease_health_recover_case` a WHERE 1 = 1");
        List<Integer> diseaseDetailIds = healthOrDiseaseByIdQueryDTO.getDiseaseDetailIds();
        if (diseaseDetailIds != null && diseaseDetailIds.size() > 0) {
            sql.append(" AND a.`disease_detail_id` IN (");
            diseaseDetailIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        List<Integer> healthIds = healthOrDiseaseByIdQueryDTO.getHealthIds();
        if (healthIds != null && healthIds.size() > 0) {
            sql.append(" OR a.`health_id` IN (");
            healthIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        totalSql += sql.toString() + ") T";
        // 获取Total总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("没有查询到对应的康复案例");
        }
        // 是否存在分页查询
        Integer pageNumber = healthOrDiseaseByIdQueryDTO.getPageNumber();
        Integer pageSize = healthOrDiseaseByIdQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql.append(" LIMIT ").append((pageNumber - 1) * pageSize).append(",").append(pageSize);
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> recoverIds = entityManager.createNativeQuery(sql.toString())
                .unwrap(NativeQueryImpl.class)
                .addScalar("recoverId", IntegerType.INSTANCE)
                .list();
        List<RecoverCaseEntity> recoverCaseEntities = recoverCaseRepository.findByIdIn(recoverIds);
        return new PageImpl<>(recoverCaseEntities, pageable, total);
    }

    @Override
    public Page<UserInfo> getRecoverCasePerson(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO) {
        Page<UserEntity> userEntities = getRecoverCaseUserEntities(healthOrDiseaseByIdQueryDTO);
        List<UserInfo> result = new ArrayList<>();
        userEntities.forEach(userEntity -> {
            if (userEntity != null && userEntity.getId() != null) {
                UserInfo userInfo = userService.getUserInfo(userEntity);
                result.add(userInfo);
            }
        });
        return new PageImpl<>(result, userEntities.getPageable(), userEntities.getTotalElements());
    }

    @Override
    public Page<UserEntity> getRecoverCaseUserEntities(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        StringBuilder sql = new StringBuilder("SELECT DISTINCT a.`user_id` AS userId FROM `vw_disease_health_recover_case` a WHERE 1 = 1");
        List<Integer> diseaseDetailIds = healthOrDiseaseByIdQueryDTO.getDiseaseDetailIds();
        if (diseaseDetailIds != null && diseaseDetailIds.size() > 0) {
            sql.append(" AND a.`disease_detail_id` IN (");
            diseaseDetailIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        List<Integer> healthIds = healthOrDiseaseByIdQueryDTO.getHealthIds();
        if (healthIds != null && healthIds.size() > 0) {
            sql.append(" OR a.`health_id` IN (");
            healthIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        totalSql += sql.toString() + ") T";
        // 获取Total总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("没有查询到对应的康复案例人数");
        }
        // 是否存在分页查询
        Integer pageNumber = healthOrDiseaseByIdQueryDTO.getPageNumber();
        Integer pageSize = healthOrDiseaseByIdQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql.append(" LIMIT ").append((pageNumber - 1) * pageSize).append(",").append(pageSize);
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Long> userIds = entityManager.createNativeQuery(sql.toString())
                .unwrap(NativeQueryImpl.class)
                .addScalar("userId", LongType.INSTANCE)
                .list();
        List<UserEntity> userEntities = userRepository.findByIdIn(userIds);
        return new PageImpl<>(userEntities, pageable, total);
    }
}
