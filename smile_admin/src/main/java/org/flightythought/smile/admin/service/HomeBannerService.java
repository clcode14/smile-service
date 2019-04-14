package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.HomeBannerEntity;
import org.flightythought.smile.admin.dto.HomeBannerDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface HomeBannerService {

    List<HomeBannerEntity> findAll();

    HomeBannerEntity findOne(Integer id);

    HomeBannerEntity create(HomeBannerDTO homebannerDTO, HttpSession session);

    HomeBannerEntity modify(HomeBannerDTO homebannerDTO, HttpSession session);

    void deleteById(Integer id);
}
