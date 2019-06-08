package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LikeData
 * @CreateTime 2019/6/8 13:42
 * @Description: 点赞数据
 */
@Data
public class LikeData<T> {
    private UserInfo likeUser;
    private T data;
}
