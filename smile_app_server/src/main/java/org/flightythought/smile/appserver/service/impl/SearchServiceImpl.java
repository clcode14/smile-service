package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.RecoverCaseSimple;
import org.flightythought.smile.appserver.bean.UserCharityFaultRecord;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.RecoverCaseEntity;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.RecoverCaseRepository;
import org.flightythought.smile.appserver.database.repository.UserCharityFaultRecordRepository;
import org.flightythought.smile.appserver.dto.SearchDTO;
import org.flightythought.smile.appserver.service.CharityFaultService;
import org.flightythought.smile.appserver.service.SearchService;
import org.flightythought.smile.appserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SearchServiceImpl
 * @CreateTime 2019/6/9 22:33
 * @Description: TODO
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RecoverCaseRepository recoverCaseRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private UserCharityFaultRecordRepository userCharityFaultRecordRepository;
    @Autowired
    private CharityFaultService charityFaultService;

    @Override
    public Page<RecoverCaseSimple> searchRecoverCase(SearchDTO searchDTO) {
        PageRequest pageRequest = PageRequest.of(searchDTO.getPageNumber() - 1, searchDTO.getPageSize());
        String name = searchDTO.getName();
        Page<RecoverCaseEntity> recoverCaseEntities = recoverCaseRepository.searchRecoverCase(name, pageRequest);
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
    public Page<UserCharityFaultRecord> searchCharityFaultRecord(SearchDTO searchDTO) {
        String name = searchDTO.getName();
        PageRequest pageRequest = PageRequest.of(searchDTO.getPageNumber() -1, searchDTO.getPageSize());
        Page<UserCharityFaultRecordEntity> userCharityFaultRecordEntities = userCharityFaultRecordRepository.searchCharityFaultRecord(name, pageRequest);
        List<UserCharityFaultRecord> userCharityFaultRecords = userCharityFaultRecordEntities.stream()
                .sorted(Comparator.comparingLong(value -> value.getCreateTime().toInstant(ZoneOffset.UTC).toEpochMilli()))
                .map(charityFaultService::getUserCharityFaultRecord).collect(Collectors.toList());
        return new PageImpl<>(userCharityFaultRecords, pageRequest, userCharityFaultRecordEntities.getTotalElements());
    }
}
