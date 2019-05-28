package org.flightythought.smile.appserver.common.utils;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import org.flightythought.smile.appserver.bean.PushMessage;
import org.flightythought.smile.appserver.database.entity.PushDataEntity;
import org.flightythought.smile.appserver.database.repository.PushDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JPushUtils
 * @CreateTime 2019/5/27 17:48
 * @Description: TODO
 */
@Component
public class JPushUtils {

    @Value("${jpush.masterSecret}")
    private String masterSecret;
    @Value("${jpush.appKey}")
    private String appKey;

    @Autowired
    private PushDataRepository pushDataRepository;

    private static final Logger LOG = LoggerFactory.getLogger(JPushUtils.class);

    /**
     * 推送数据
     *
     * @param message 推送消息实体类
     * @param userId  推送用户ID
     */
    @Async
    public void pushData(PushMessage message, Long userId) {
        try {
            // 将推送数据插入数据库
            PushDataEntity pushDataEntity = new PushDataEntity();
            // 用户ID
            pushDataEntity.setUserId(userId);
            // 数据JSON
            String json = JSON.toJSONString(message);
            pushDataEntity.setData(json);
            // 是否已读
            pushDataEntity.setRead(false);
            // type
            pushDataEntity.setType(message.getType());
            // code
            pushDataEntity.setCode(message.getCode());
            // createUserName
            pushDataEntity.setCreateUserName(userId + "");
            pushDataEntity = pushDataRepository.save(pushDataEntity);
            message.setPushDataId(pushDataEntity.getId());
            String pushDataJson = JSON.toJSONString(message);
            JPushClient jPushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
            PushPayload pushPayload = PushPayload.newBuilder()
                    // 推送所有平台
                    .setPlatform(Platform.all())
                    // 设置别名
                    .setAudience(Audience.alias(userId + ""))
                    // 设置消息
                    .setNotification(Notification.alert(pushDataJson)).build();
            // 推送消息
            jPushClient.sendPush(pushPayload);
            jPushClient.close();
        } catch (Exception e) {
            LOG.error("推送信息失败", e);
        }
    }
}
