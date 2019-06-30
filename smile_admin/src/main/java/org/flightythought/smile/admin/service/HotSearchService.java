package org.flightythought.smile.admin.service;

import java.util.List;

import org.flightythought.smile.admin.bean.HotSearchInfo;
import org.flightythought.smile.admin.dto.HotSearchDTO;

public interface HotSearchService {
    
    void addHotSearch(HotSearchDTO hotSearchDTO);

    List<HotSearchInfo> getHotSearchList();

    HotSearchInfo getHotSearch(Integer id);

    void updateHotSearch(HotSearchDTO hotSearchDTO);

    void deleteHotSearch(Integer id);

}
