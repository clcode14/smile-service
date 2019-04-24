package org.flightythought.smile.admin.service;


import org.flightythought.smile.admin.database.entity.RecoverCaseEntity;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface RecoverService {

    Page<RecoverCaseEntity> getRecoverCaseEntities(Map<String,String> params);

}
