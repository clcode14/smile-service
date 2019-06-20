package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.AppUpdateData;
import org.flightythought.smile.appserver.bean.HiddenConfig;
import org.flightythought.smile.appserver.bean.NoticeNumber;
import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.AppVersionEntity;
import org.flightythought.smile.appserver.database.entity.PushDataEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.entity.UserSettingEntity;
import org.flightythought.smile.appserver.database.repository.AppVersionRepository;
import org.flightythought.smile.appserver.database.repository.PushDataRepository;
import org.flightythought.smile.appserver.database.repository.UserSettingRepository;
import org.flightythought.smile.appserver.dto.HiddenConfigDTO;
import org.flightythought.smile.appserver.dto.NoticeQueryDTO;
import org.flightythought.smile.appserver.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SystemServiceImpl
 * @CreateTime 2019/5/27 2:10
 * @Description: TODO
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private UserSettingRepository userSettingRepository;
    @Autowired
    private AppVersionRepository appVersionRepository;
    @Autowired
    private PushDataRepository pushDataRepository;

    @Override
    public AppUpdateData updateApp() {
        AppUpdateData appUpdateData = new AppUpdateData();
        AppVersionEntity appVersionEntity = appVersionRepository.findByCurrent(true);
        if (appVersionEntity != null) {
            appUpdateData.setDescription(appVersionEntity.getDescription());
            appUpdateData.setUrl(appVersionEntity.getUrl());
            appUpdateData.setForceUpdate(appVersionEntity.getForceUpdate());
            appUpdateData.setVersion(appVersionEntity.getVersion());
            appUpdateData.setVersionId(appVersionEntity.getVersionId());
            return appUpdateData;
        } else {
            throw new FlightyThoughtException("暂无数据");
        }
    }

    @Override
    public UserSettingEntity getSettingByCode(String code, String defaultValue) {
        // 当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        // 查询该用户配置
        UserSettingEntity userSettingEntity = userSettingRepository.findByUserIdAndCode(userId, code);
        if (userSettingEntity == null) {
            userSettingEntity = new UserSettingEntity();
            userSettingEntity.setCode(code);
            userSettingEntity.setValue(defaultValue);
            userSettingEntity.setUserId(userId);
            userSettingEntity.setCreateUserName(userId + "");
            userSettingEntity = userSettingRepository.save(userSettingEntity);
        }
        return userSettingEntity;
    }

    @Override
    public UserSettingEntity updateSettingByCode(String value, String code) {
        // 当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        // 查询该用户配置
        UserSettingEntity userSettingEntity = userSettingRepository.findByUserIdAndCode(userId, code);
        if (userSettingEntity != null) {
            userSettingEntity.setValue(value);
            userSettingEntity.setUpdateUserName(userId + "");
            userSettingEntity = userSettingRepository.save(userSettingEntity);
        } else {
            userSettingEntity = new UserSettingEntity();
            userSettingEntity.setValue(value);
            userSettingEntity.setUserId(userId);
            userSettingEntity.setCode(code);
            userSettingEntity.setCreateUserName(userId + "");
            userSettingEntity = userSettingRepository.save(userSettingEntity);
        }
        return userSettingEntity;
    }

    @Override
    public PushDataEntity readNotice(Integer pushDataId) {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        PushDataEntity pushDataEntity = pushDataRepository.findById(pushDataId);
        if (!userEntity.getId().equals(pushDataEntity.getUserId())) {
            throw new FlightyThoughtException("该用户对此消息无权限查看");
        }
        pushDataEntity.setRead(true);
        pushDataRepository.save(pushDataEntity);
        return pushDataEntity;
    }

    @Override
    public NoticeNumber getNotReadNoticeNumber() {
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        // 系统通知数
        int systemNumber = pushDataRepository.countByTypeAndUserId(Constants.NOTICE_SYSTEM, userId);
        // 点赞通知
        int likeNumber = pushDataRepository.countByTypeAndUserId(Constants.NOTICE_LIKE, userId);
        // 粉丝通知
        int fansNumber = pushDataRepository.countByTypeAndUserId(Constants.NOTICE_FANS, userId);
        // 评论通知
        int messageNumber = pushDataRepository.countByTypeAndUserId(Constants.NOTICE_MESSAGE, userId);
        NoticeNumber noticeNumber = new NoticeNumber();
        noticeNumber.setSystemNotice(systemNumber);
        noticeNumber.setLikeNotice(likeNumber);
        noticeNumber.setFansNotice(fansNumber);
        noticeNumber.setMessageNotice(messageNumber);
        return noticeNumber;
    }

    @Override
    public Page<PushDataEntity> getNotReadNotice(NoticeQueryDTO noticeQueryDTO) {
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        Integer type = noticeQueryDTO.getType();
        PageRequest pageRequest = PageRequest.of(noticeQueryDTO.getPageNumber() - 1, noticeQueryDTO.getPageSize());
        Page<PushDataEntity> pushDataEntities = pushDataRepository.findByUserIdAndReadAndTypeOrderByCreateTimeDesc(userId, false, type, pageRequest);
        // 将消息置为已读
        pushDataEntities.forEach(pushDataEntity -> {
            pushDataEntity.setRead(true);
            pushDataRepository.save(pushDataEntity);
        });
        return pushDataEntities;
    }

    @Override
    public Page<PushDataEntity> getNotice(NoticeQueryDTO noticeQueryDTO) {
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        Integer type = noticeQueryDTO.getType();
        Boolean read = noticeQueryDTO.getRead();
        PageRequest pageRequest = PageRequest.of(noticeQueryDTO.getPageNumber() - 1, noticeQueryDTO.getPageSize());
        if (read == null) {
            return pushDataRepository.findByUserIdAndTypeOrderByCreateTimeDesc(userId, type, pageRequest);
        } else {
            return pushDataRepository.findByUserIdAndReadAndTypeOrderByCreateTimeDesc(userId, read, type, pageRequest);
        }
    }

    @Override
    public HiddenConfig getHiddenConfig() {
        // 动态隐私
        UserSettingEntity dynamicSettingEntity = getSettingByCode(Constants.DYNAMIC_HIDDEN, "false");
        // 行善隐私
        UserSettingEntity charitySettingEntity = getSettingByCode(Constants.CHARITY_HIDDEN, "false");
        // 过失隐私
        UserSettingEntity faultSettingEntity = getSettingByCode(Constants.FAULT_HIDDEN, "false");
        HiddenConfig hiddenConfig = new HiddenConfig();
        hiddenConfig.setCharityHidden(Boolean.valueOf(charitySettingEntity.getValue()));
        hiddenConfig.setFaultHidden(Boolean.valueOf(faultSettingEntity.getValue()));
        hiddenConfig.setDynamicHidden(Boolean.valueOf(dynamicSettingEntity.getValue()));
        return hiddenConfig;
    }

    @Override
    public HiddenConfig saveHiddenConfig(HiddenConfigDTO hiddenConfigDTO) {
        UserSettingEntity charitySettingEntity = updateSettingByCode(hiddenConfigDTO.getCharityHidden() + "", Constants.CHARITY_HIDDEN);
        UserSettingEntity faultSettingEntity = updateSettingByCode(hiddenConfigDTO.getFaultHidden() + "", Constants.FAULT_HIDDEN);
        UserSettingEntity dynamicSettingEntity = updateSettingByCode(hiddenConfigDTO.getDynamicHidden() + "", Constants.DYNAMIC_HIDDEN);
        HiddenConfig hiddenConfig = new HiddenConfig();
        hiddenConfig.setDynamicHidden(Boolean.valueOf(dynamicSettingEntity.getValue()));
        hiddenConfig.setFaultHidden(Boolean.valueOf(faultSettingEntity.getValue()));
        hiddenConfig.setCharityHidden(Boolean.valueOf(charitySettingEntity.getValue()));
        return hiddenConfig;
    }
}
