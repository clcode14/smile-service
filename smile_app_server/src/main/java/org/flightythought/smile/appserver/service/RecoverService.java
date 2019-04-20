package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.RecoverCaseSimple;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.database.entity.RecoverCaseEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.dto.AboutDiseaseDetailQueryDTO;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseByIdQueryDTO;
import org.springframework.data.domain.Page;

public interface RecoverService {
    Page<RecoverCaseSimple> getRecoverCase(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO);

    Page<RecoverCaseEntity> getRecoverCaseEntities(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO);

    Page<UserInfo> getRecoverCasePerson(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO);

    Page<UserEntity> getRecoverCaseUserEntities(HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO);
}
