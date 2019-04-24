package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.HomeBannerEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.HomeBannerRepository;
import org.flightythought.smile.admin.dto.HomeBannerDTO;
import org.flightythought.smile.admin.service.HomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements HomeBannerService {
    @Autowired
    private HomeBannerRepository homeBannerRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public List<HomeBannerEntity> findAll() {
        return homeBannerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingInt(HomeBannerEntity::getSort))
                .map(homeBannerEntity -> {
                    String domainPort = platformUtils.getDomainPort();
                    ImagesEntity imagesEntity = homeBannerEntity.getImagesEntity();
                    String imageUrlByPath = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
                    imagesEntity.setPath(imageUrlByPath);
                    homeBannerEntity.setImagesEntity(imagesEntity);
                    return homeBannerEntity;
                }).collect(Collectors.toList());
    }

    @Override
    public HomeBannerEntity findOne(Integer id) {
        AtomicReference<HomeBannerEntity> homeBannerEntity = new AtomicReference<>();
        homeBannerRepository.findById(id)
                .ifPresent(homeBanner -> {
                    String domainPort = platformUtils.getDomainPort();
                    ImagesEntity imagesEntity = homeBanner.getImagesEntity();
                    String imageUrlByPath = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
                    imagesEntity.setPath(imageUrlByPath);
                    homeBanner.setImagesEntity(imagesEntity);
                    homeBannerEntity.set(homeBanner);
                });
        return homeBannerEntity.get();
    }

    @Override
    public HomeBannerEntity create(HomeBannerDTO homebannerDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);

        HomeBannerEntity homeBannerEntity = new HomeBannerEntity();
        homeBannerEntity.setSort(homebannerDTO.getSort());
        homeBannerEntity.setLink(homebannerDTO.getLink());
        homeBannerEntity.setTitle(homebannerDTO.getTitle());
        homeBannerEntity.setImageId(homebannerDTO.getImageId());
        homeBannerEntity.setCreateUserName(sysUserEntity.getUserName());
        return homeBannerRepository.save(homeBannerEntity);
    }

    @Override
    public HomeBannerEntity modify(HomeBannerDTO homebannerDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);

        HomeBannerEntity homeBannerEntity = new HomeBannerEntity();
        homeBannerEntity.setSort(homebannerDTO.getSort());
        homeBannerEntity.setLink(homebannerDTO.getLink());
        homeBannerEntity.setTitle(homebannerDTO.getTitle());
        homeBannerEntity.setImageId(homebannerDTO.getImageId());
        homeBannerEntity.setUpdateUserName(sysUserEntity.getUserName());
        homeBannerEntity.setId(homebannerDTO.getId());
        return homeBannerRepository.save(homeBannerEntity);
    }

    @Override
    public void deleteById(Integer id) {
        homeBannerRepository.deleteById(id);
    }
}
