package org.flightythought.smile.appserver.service;

import java.util.List;

import org.flightythought.smile.appserver.bean.HotSearchInfo;

public interface HotSearchService {
    
    List<HotSearchInfo> getHotSearchList();

}
