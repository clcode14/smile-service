package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.RecoverCaseSimple;
import org.flightythought.smile.appserver.bean.UserCharityFaultRecord;
import org.flightythought.smile.appserver.dto.SearchDTO;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SearchService
 * @CreateTime 2019/6/9 22:32
 * @Description: TODO
 */
public interface SearchService {
    Page<RecoverCaseSimple> searchRecoverCase(SearchDTO searchDTO);

    Page<UserCharityFaultRecord> searchCharityFaultRecord(SearchDTO searchDTO);
}
