package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.HomeBanner;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.HomeBannerEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.repository.HomeBannerRepository;
import org.flightythought.smile.appserver.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HomeServiceImpl
 * @CreateTime 2019/4/14 19:32
 * @Description: TODO
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeBannerRepository homeBannerRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<HomeBanner> getHomeBanners() {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        List<HomeBannerEntity> homeBannerEntities = homeBannerRepository.findAll(sort);
        List<HomeBanner> homeBanners = new ArrayList<>();
        String domainPort = platformUtils.getDomainPort();
        homeBannerEntities.forEach(homeBannerEntity -> {
            HomeBanner homeBanner = new HomeBanner();
            // 主键ID
            homeBanner.setId(homeBannerEntity.getId());
            // 标题
            homeBanner.setTitle(homeBannerEntity.getTitle());
            // 连接
            homeBanner.setLink(homeBannerEntity.getLink());
            // 图片
            ImagesEntity imagesEntity = homeBannerEntity.getImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setSize(imagesEntity.getSize());
                imageInfo.setName(imagesEntity.getFileName());
                String url = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                imageInfo.setUrl(url);
                homeBanner.setImage(imageInfo);
            }
            homeBanner.setSort(homeBannerEntity.getSort());
            homeBanners.add(homeBanner);
        });
        return homeBanners;
    }
}
