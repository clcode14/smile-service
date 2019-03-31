package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.database.entity.RecoverCase;
import org.flightythought.smile.appserver.dto.RecoverCaseDTO;
import org.springframework.data.domain.Page;

public interface RecoverService {
    /**
     * 获取康复案例列表
     *
     * @param recoverCaseDTO 康复案例DTO
     */
    Page<RecoverCase> findAllRecoverCasePage(RecoverCaseDTO recoverCaseDTO);
}
