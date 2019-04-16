package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.CaseAuditInfo;
import org.flightythought.smile.admin.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.admin.database.entity.JourneyEntity;
import org.flightythought.smile.admin.database.repository.JourneyNormRepository;
import org.flightythought.smile.admin.database.repository.JourneyRepository;
import org.flightythought.smile.admin.database.repository.JourneyToReportRepository;
import org.flightythought.smile.admin.database.repository.UserRepository;
import org.flightythought.smile.admin.service.CaseAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaseAuditServiceImpl implements CaseAuditService {

    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyToReportRepository journeyToReportRepository;
    @Autowired
    private JourneyNormRepository journeyNormRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<CaseAuditInfo> findAll(Map<String, String> params) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        Page<JourneyEntity> page = journeyRepository.findAll(pageRequest);
        List<CaseAuditInfo> auditInfos = page
                .getContent()
                .stream()
                .map(journeyEntity -> {
                    CaseAuditInfo caseAuditInfo = new CaseAuditInfo();
                    caseAuditInfo.setJourneyId(journeyEntity.getJourneyId());
                    caseAuditInfo.setJourneyName(journeyEntity.getJourneyName());
                    userRepository.findById(journeyEntity.getUserId())
                            .ifPresent(userEntity -> {
                                caseAuditInfo.setUserName(userEntity.getUsername());
                            });
                    return caseAuditInfo;
                }).collect(Collectors.toList());

        PageImpl<CaseAuditInfo> result = new PageImpl<>(auditInfos, pageRequest, page.getTotalElements());
        return result;
    }
}
