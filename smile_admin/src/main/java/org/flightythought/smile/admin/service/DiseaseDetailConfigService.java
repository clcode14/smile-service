package org.flightythought.smile.admin.service;

        import org.flightythought.smile.admin.bean.DiseaseClass;
        import org.flightythought.smile.admin.bean.DiseaseClassDetailInfo;
        import org.flightythought.smile.admin.database.entity.DiseaseClassDetailEntity;
        import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
        import org.flightythought.smile.admin.database.entity.SysUserEntity;
        import org.flightythought.smile.admin.dto.DiseaseClassDetailDTO;
        import org.springframework.data.domain.Page;

        import java.util.List;

public interface DiseaseDetailConfigService {
    List<DiseaseClass> getDiseaseClass();

    Page<DiseaseClassDetailInfo> getDiseaseDetails(int diseaseId, int pageNumber, int pageSize);

    Page<DiseaseClassDetailInfo> getDiseaseClassDetailInfo(Page<DiseaseClassDetailEntity> diseaseClassDetailEntities);

    DiseaseClassDetailEntity saveDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO);

    DiseaseClassDetailEntity updateDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO);

    void deleteDiseaseDetail(Integer id);
}
