package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.OfficeInfo;
import org.flightythought.smile.admin.database.entity.OfficeEntity;
import org.flightythought.smile.admin.dto.OfficeDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface OfficeService {

    OfficeEntity save(OfficeDTO officeDTO, HttpSession session);

    OfficeEntity modify(OfficeDTO officeDTO, HttpSession session);

    void deleteById(Long officeId, HttpSession session);

    Page<OfficeInfo> findAllOffice(Map<String,String> params, HttpSession session);

    OfficeInfo findOffice(Long officeId, HttpSession session);

    Page<OfficeInfo> getOfficeInfo(Page<OfficeEntity> officeEntities);
}
