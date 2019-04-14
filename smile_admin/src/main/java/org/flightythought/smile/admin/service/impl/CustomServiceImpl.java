package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.CustomEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.CustomRepository;
import org.flightythought.smile.admin.dto.CustomDTO;
import org.flightythought.smile.admin.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    private CustomRepository customRepository;

    @Override
    @Transactional
    public CustomEntity save(CustomDTO customDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        CustomEntity customEntity = new CustomEntity();
        customEntity.setName(customDTO.getName());
        customEntity.setPhone(customDTO.getPhone());
        customEntity.setCreateUserName(sysUserEntity.getUserName());
        return customRepository.save(customEntity);
    }

    @Override
    @Transactional
    public CustomEntity modify(CustomDTO customDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        CustomEntity customEntity = new CustomEntity();
        customEntity.setId(customDTO.getId());
        customEntity.setName(customDTO.getName());
        customEntity.setPhone(customDTO.getPhone());
        customEntity.setUpdateUserName(sysUserEntity.getUserName());
        return customRepository.save(customEntity);
    }

    @Override
    public void deleteById(Long id) {
        customRepository.deleteById(id);
    }

    @Override
    public Page<CustomEntity> findAll(Map<String, String> params) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String phone = params.get("phone");

        CustomEntity customEntity = new CustomEntity();
        if (StringUtils.isNotBlank(phone)) {
            customEntity.setPhone(phone);
        }
        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        return customRepository.findAll(Example.of(customEntity), pageRequest);
    }

    @Override
    public CustomEntity findCustom(Long id) {
        AtomicReference<CustomEntity> customEntity = new AtomicReference<>();
        customRepository.findById(id)
                .ifPresent(custom -> {
                    customEntity.set(custom);
                });
        return customEntity.get();
    }
}
