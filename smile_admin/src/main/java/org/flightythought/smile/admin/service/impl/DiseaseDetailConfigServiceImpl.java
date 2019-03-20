package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.DiseaseClassDetailRepository;
import org.flightythought.smile.admin.database.repository.DiseaseClassRepository;
import org.flightythought.smile.admin.dto.DiseaseClassDetailDTO;
import org.flightythought.smile.admin.service.DiseaseDetailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiseaseDetailConfigServiceImpl implements DiseaseDetailConfigService {

    @Autowired
    private DiseaseClassRepository diseaseClassRepository;
    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;

    @Override
    public List<DiseaseClassEntity> getDiseaseClass() {
        return diseaseClassRepository.findAll();
    }

    @Override
    public Page<DiseaseClassDetailEntity> getDiseaseDetails(int diseaseId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return diseaseClassDetailRepository.findByDiseaseId(diseaseId, pageable);
    }

    @Override
    public DiseaseClassDetailEntity saveDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO) {
        DiseaseClassDetailEntity diseaseClassDetailEntity = new DiseaseClassDetailEntity();
        diseaseClassDetailEntity.setDiseaseDetailName(diseaseClassDetailDTO.getDiseaseDetailName());
        diseaseClassDetailEntity.setNumber(diseaseClassDetailDTO.getNumber());
        diseaseClassDetailEntity.setDiseaseId(diseaseClassDetailDTO.getDiseaseId());
        diseaseClassDetailEntity.setCreateUserName(sysUserEntity.getLoginName());
        diseaseClassDetailEntity.setCreateTime(LocalDateTime.now());
        DiseaseClassDetailEntity result = diseaseClassDetailRepository.save(diseaseClassDetailEntity);
        return result;
    }

    @Override
    public DiseaseClassDetailEntity updateDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO) {
        int diseaseDetailId = diseaseClassDetailDTO.getDiseaseDetailId();
        DiseaseClassDetailEntity diseaseClassDetailEntity = diseaseClassDetailRepository.findByDiseaseDetailId(diseaseDetailId);
        diseaseClassDetailEntity.setDiseaseId(diseaseClassDetailDTO.getDiseaseId());
        diseaseClassDetailEntity.setNumber(diseaseClassDetailDTO.getNumber());
        diseaseClassDetailEntity.setDiseaseDetailName(diseaseClassDetailDTO.getDiseaseDetailName());
        diseaseClassDetailEntity.setUpdateUserName(sysUserEntity.getLoginName());
        diseaseClassDetailEntity.setUpdateTime(LocalDateTime.now());
        return diseaseClassDetailRepository.save(diseaseClassDetailEntity);
    }

    @Override
    public void deleteDiseaseDetail(Integer id) {
        DiseaseClassDetailEntity diseaseClassDetailEntity = diseaseClassDetailRepository.findByDiseaseDetailId(id);
        diseaseClassDetailRepository.delete(diseaseClassDetailEntity);
    }
}
