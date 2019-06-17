package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.AppUpdateData;
import org.flightythought.smile.appserver.bean.HiddenConfig;
import org.flightythought.smile.appserver.bean.NoticeNumber;
import org.flightythought.smile.appserver.database.entity.PushDataEntity;
import org.flightythought.smile.appserver.database.entity.UserSettingEntity;
import org.flightythought.smile.appserver.dto.HiddenConfigDTO;
import org.flightythought.smile.appserver.dto.NoticeQueryDTO;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SystemService
 * @CreateTime 2019/5/27 2:00
 * @Description: TODO
 */
public interface SystemService {
    AppUpdateData updateApp();

    UserSettingEntity getSettingByCode(String code, String defaultValue);

    UserSettingEntity updateSettingByCode(String value, String code);

    PushDataEntity readNotice(Integer pushDataId);

    NoticeNumber getNotReadNoticeNumber();

    Page<PushDataEntity> getNotReadNotice(NoticeQueryDTO noticeQueryDTO);

    Page<PushDataEntity> getNotice(NoticeQueryDTO noticeQueryDTO);

    HiddenConfig getHiddenConfig();

    HiddenConfig saveHiddenConfig(HiddenConfigDTO hiddenConfig);
}
