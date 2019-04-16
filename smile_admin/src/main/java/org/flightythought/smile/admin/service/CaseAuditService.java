package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.CaseAuditInfo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface CaseAuditService {

    Page<CaseAuditInfo> findAll(Map<String,String> params);
}
