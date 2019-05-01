package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.DynamicDetailMessageSimple;
import org.flightythought.smile.appserver.bean.DynamicDetailSimple;
import org.flightythought.smile.appserver.bean.DynamicSimple;
import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.flightythought.smile.appserver.database.entity.DynamicDetailsEntity;
import org.flightythought.smile.appserver.database.entity.DynamicEntity;
import org.flightythought.smile.appserver.dto.AddDynamicDTO;
import org.flightythought.smile.appserver.dto.AddDynamicDetailDTO;
import org.flightythought.smile.appserver.dto.DynamicDetailMessageDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DynamicService {
    DynamicSimple addDynamic(AddDynamicDTO addDynamicDTO);

    Page<DynamicSimple> getMyDynamic(PageFilterDTO pageFilterDTO);

    Page<DynamicEntity> getDynamics(PageFilterDTO pageFilterDTO, Long userId, Boolean hidden);

    DynamicSimple getDynamicSimple(DynamicEntity dynamicEntity);

    DynamicDetailSimple addDynamicDetail(AddDynamicDetailDTO addDynamicDetailDTO);

    DynamicDetailSimple getDynamicDetailSimple(DynamicDetailsEntity dynamicDetailsEntity);

    DynamicDetailMessageEntity addDynamicDetailMessage(DynamicDetailMessageDTO dynamicDetailMessageDTO);

    List<DynamicDetailMessageSimple> getDynamicDetailMessage(Integer dynamicDetailId);
}
