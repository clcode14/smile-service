package org.flightythought.smile.admin.bean;

import java.time.LocalDateTime;

import org.flightythought.smile.admin.database.entity.UserEntity;
import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * APP用户信息
 * 
 * @author cl47872
 * @version $Id: AppUserInfo.java, v 0.1 Jun 4, 2019 9:08:03 PM cl47872 Exp $
 */
public class AppUserInfo {
    /**
     * 手机号
     */
    private String        mobile;

    /**
     * 昵称
     */
    private String        nickName;

    /**
     * 推荐人
     */
    private String        recommenderNickName;

    /**
     * 注册时间
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRecommenderNickName() {
        return recommenderNickName;
    }

    public void setRecommenderNickName(String recommenderNickName) {
        this.recommenderNickName = recommenderNickName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
