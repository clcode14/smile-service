package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.database.entity.RecoverCaseEntity;
import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.flightythought.smile.admin.database.repository.RecoverCaseRepository;
import org.flightythought.smile.admin.service.RecoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecoverServiceImpl implements RecoverService {

    @Autowired
    private RecoverCaseRepository recoverCaseRepository;

    @Override
    public Page<RecoverCaseEntity> getRecoverCaseEntities(Map<String, String> params) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String title = params.get("title");

        RecoverCaseEntity recoverCaseEntity = new RecoverCaseEntity();
        if (StringUtils.isNotBlank(title)) {
            recoverCaseEntity.setTitle(title);
        }

        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        recoverCaseRepository.findAll(Example.of(recoverCaseEntity), pageRequest);
        return recoverCaseRepository.findAll(Example.of(recoverCaseEntity), pageRequest);
    }
}
