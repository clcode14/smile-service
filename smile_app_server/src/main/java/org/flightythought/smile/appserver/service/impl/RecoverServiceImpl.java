package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.database.entity.RecoverCase;
import org.flightythought.smile.appserver.database.repository.RecoverCaseRepository;
import org.flightythought.smile.appserver.dto.RecoverCaseDTO;
import org.flightythought.smile.appserver.service.RecoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoverServiceImpl implements RecoverService {
    @Autowired
    private RecoverCaseRepository recoverCaseRepository;

    @Override
    public Page<RecoverCase> findAllRecoverCasePage(RecoverCaseDTO recoverCaseDTO) {
        if (recoverCaseDTO != null) {
            if (recoverCaseDTO.getPageNumber() != null && recoverCaseDTO.getPageSize() != null) {
                // 分页查询
                PageRequest pageRequest = PageRequest.of(recoverCaseDTO.getPageNumber() - 1, recoverCaseDTO.getPageSize());
                RecoverCase recoverCase = new RecoverCase();
                if (recoverCaseDTO.getDiseaseDetailId() != null) {
                    recoverCase.setDiseaseDetailId(recoverCaseDTO.getDiseaseDetailId());
                }
                return recoverCaseRepository.findAll(Example.of(recoverCase), pageRequest);
            } else {
                RecoverCase recoverCase = new RecoverCase();
                if (recoverCaseDTO.getDiseaseDetailId() != null) {
                    recoverCase.setDiseaseDetailId(recoverCaseDTO.getDiseaseDetailId());
                }
                List<RecoverCase> recoverCases = recoverCaseRepository.findAll(Example.of(recoverCase));
                if (recoverCases.size() > 0) {
                    return new PageImpl<>(recoverCases, PageRequest.of(0, recoverCases.size()), recoverCases.size());
                }
            }
        }
        return null;
    }
}
