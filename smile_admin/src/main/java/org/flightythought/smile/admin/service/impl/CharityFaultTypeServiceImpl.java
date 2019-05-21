package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.CharityFaultTypeRepository;
import org.flightythought.smile.admin.service.CharityFaultTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 15:48
 * @Description: TODO
 */
@Service
public class CharityFaultTypeServiceImpl implements CharityFaultTypeService {

    @Autowired
    private CharityFaultTypeRepository charityFaultTypeRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public Page<CharityFaultTypeEntity> getCharityFaultTypes(Integer pageSize, Integer pageNumber) {
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            List<CharityFaultTypeEntity> charityFaultTypeEntities = charityFaultTypeRepository.findAll();
            return new PageImpl<>(charityFaultTypeEntities, PageRequest.of(0, charityFaultTypeEntities.size() + 1), charityFaultTypeEntities.size());
        }
        return charityFaultTypeRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
    }

    @Override
    public CharityFaultTypeEntity addCharityFaultType(CharityFaultTypeEntity charityFaultTypeEntity) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        charityFaultTypeEntity.setCreateUserName(userEntity.getLoginName());
        return charityFaultTypeRepository.save(charityFaultTypeEntity);
    }

    @Override
    public CharityFaultTypeEntity modifyCharityFaultType(CharityFaultTypeEntity charityFaultTypeEntity) {
        CharityFaultTypeEntity entity = charityFaultTypeRepository.findByCfTypeId(charityFaultTypeEntity.getCfTypeId());
        if (entity != null) {
            charityFaultTypeEntity.setCreateUserName(entity.getCreateUserName());
            charityFaultTypeEntity.setCreateTime(entity.getCreateTime());
            SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
            charityFaultTypeEntity.setUpdateUserName(userEntity.getLoginName());
            BeanUtils.copyProperties(charityFaultTypeEntity, entity);
            return charityFaultTypeRepository.save(entity);
        }
        return null;
    }

    @Override
    public void deleteCharityFaultType(Integer cfTypeId) {
        CharityFaultTypeEntity entity = charityFaultTypeRepository.findByCfTypeId(cfTypeId);
        if (entity != null) {
            charityFaultTypeRepository.delete(entity);
        }
    }
}
