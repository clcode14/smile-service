package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.dto.DiseaseClassDetailDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiseaseDetailConfigService {
    List<DiseaseClassEntity> getDiseaseClass();

    Page<DiseaseClassDetailEntity> getDiseaseDetails(int diseaseId, int pageNumber, int pageSize);

    DiseaseClassDetailEntity saveDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO);

    DiseaseClassDetailEntity updateDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO);

    void deleteDiseaseDetail(Integer id);
}
