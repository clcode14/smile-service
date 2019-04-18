package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.HomeBanner;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HomeService
 * @CreateTime 2019/4/14 19:30
 * @Description: TODO
 */
public interface HomeService {
    List<HomeBanner> getHomeBanners();
}
