//package org.flightythought.smile.appserver.service.impl;
//
//import org.flightythought.smile.appserver.database.entity.RecoverCaseEntity;
//import org.flightythought.smile.appserver.database.repository.RecoverCaseRepository;
//import org.flightythought.smile.appserver.dto.AboutDiseaseDetailQueryDTO;
//import org.flightythought.smile.appserver.service.RecoverService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RecoverServiceImpl implements RecoverService {
//    @Autowired
//    private RecoverCaseRepository recoverCaseRepository;
//
//    @Override
//    public Page<RecoverCaseEntity> findAllRecoverCasePage(AboutDiseaseDetailQueryDTO aboutDiseaseDetailQueryDTO) {
//        if (aboutDiseaseDetailQueryDTO != null) {
//            if (aboutDiseaseDetailQueryDTO.getPageNumber() != null && aboutDiseaseDetailQueryDTO.getPageSize() != null) {
//                // 分页查询
//                PageRequest pageRequest = PageRequest.of(aboutDiseaseDetailQueryDTO.getPageNumber() - 1, aboutDiseaseDetailQueryDTO.getPageSize());
//                RecoverCaseEntity recoverCaseEntity = new RecoverCaseEntity();
//                if (aboutDiseaseDetailQueryDTO.getDiseaseDetailId() != null) {
//                    recoverCaseEntity.setDiseaseDetailId(aboutDiseaseDetailQueryDTO.getDiseaseDetailId());
//                }
//                return recoverCaseRepository.findAll(Example.of(recoverCaseEntity), pageRequest);
//            } else {
//                RecoverCaseEntity recoverCaseEntity = new RecoverCaseEntity();
//                if (aboutDiseaseDetailQueryDTO.getDiseaseDetailId() != null) {
//                    recoverCaseEntity.setDiseaseDetailId(aboutDiseaseDetailQueryDTO.getDiseaseDetailId());
//                }
//                List<RecoverCaseEntity> recoverCaseEntities = recoverCaseRepository.findAll(Example.of(recoverCaseEntity));
//                if (recoverCaseEntities.size() > 0) {
//                    return new PageImpl<>(recoverCaseEntities, PageRequest.of(0, recoverCaseEntities.size()), recoverCaseEntities.size());
//                }
//            }
//        }
//        return null;
//    }
//}
