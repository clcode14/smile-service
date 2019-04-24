package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.CaseAuditInfo;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface CaseAuditService {

    Page<CaseAuditInfo> findAll(Map<String,String> params);

    void addCase(Map<String, String> params, HttpSession session) throws Exception;

    void cancelCase(Integer id) throws Exception;

    void doAudit(Map<String,String> params, HttpSession session) throws FlightyThoughtException;
}
