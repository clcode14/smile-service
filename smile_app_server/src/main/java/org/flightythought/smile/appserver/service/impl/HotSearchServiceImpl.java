package org.flightythought.smile.appserver.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.flightythought.smile.appserver.bean.HotSearchInfo;
import org.flightythought.smile.appserver.database.entity.HotSearchEntity;
import org.flightythought.smile.appserver.database.repository.HotSearchRepository;
import org.flightythought.smile.appserver.service.HotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotSearchServiceImpl implements HotSearchService {
    @Autowired
    private HotSearchRepository hotSearchRepository;

    @Override
    public List<HotSearchInfo> getHotSearchList() {
        List<HotSearchEntity> list = hotSearchRepository.findAll();
        return list.stream().map(e -> {
            HotSearchInfo hotSearchInfo = new HotSearchInfo();
            hotSearchInfo.setId(e.getId());
            hotSearchInfo.setCode(e.getCode());
            hotSearchInfo.setDescription(e.getDescription());
            return hotSearchInfo;
        }).collect(Collectors.toList());
    }
}
