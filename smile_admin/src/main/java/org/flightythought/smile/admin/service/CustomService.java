package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.CustomEntity;
import org.flightythought.smile.admin.dto.CustomDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface CustomService {

    CustomEntity save(CustomDTO customDTO,HttpSession session);

    CustomEntity modify(CustomDTO customDTO,HttpSession session);

    void deleteById(Long id);

    Page<CustomEntity>  findAll(Map<String,String> params);

    CustomEntity findCustom(Long id);
}
