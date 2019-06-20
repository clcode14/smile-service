package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.AppUpdateData;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ViewService
 * @CreateTime 2019/6/21 1:11
 * @Description: TODO
 */
public interface ViewService {
    AppUpdateData register(String phone, String vCode, String token, String recommender);
}
