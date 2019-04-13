package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.OfficeEntity;
import org.flightythought.smile.admin.dto.OfficeDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface OfficeService {

    OfficeEntity save(OfficeDTO officeDTO, HttpSession session);

    OfficeEntity modify(OfficeDTO officeDTO, HttpSession session);

    void deleteById(Integer officeId, HttpSession session);

    Page<OfficeEntity> findAllOffice(Map<String,String> params, HttpSession session);

    OfficeEntity findOffice(Integer officeId, HttpSession session);
}
