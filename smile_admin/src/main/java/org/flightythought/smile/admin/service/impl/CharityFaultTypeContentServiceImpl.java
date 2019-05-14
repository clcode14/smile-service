package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.bean.CharityFaultTypeContent;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeContentEntity;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.CharityFaultTypeContentRepository;
import org.flightythought.smile.admin.database.repository.CharityFaultTypeRepository;
import org.flightythought.smile.admin.service.CharityFaultTypeContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 16:44
 * @Description: TODO
 */
@Service
public class CharityFaultTypeContentServiceImpl implements CharityFaultTypeContentService {
    @Autowired
    private CharityFaultTypeContentRepository charityFaultTypeContentRepository;
    @Autowired
    private CharityFaultTypeRepository charityFaultTypeRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public Page<CharityFaultTypeContent> getCharityFaultTypeContents(Integer pageSize, Integer pageNumber, Integer cfTypeId) {
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            List<CharityFaultTypeContentEntity> charityFaultTypeContentEntities;
            if (cfTypeId != null) {
                charityFaultTypeContentEntities = charityFaultTypeContentRepository.findByCfTypeId(cfTypeId);
            } else {
                charityFaultTypeContentEntities = charityFaultTypeContentRepository.findAll();
            }
            List<Integer> cfTypeIds = charityFaultTypeContentEntities.stream().map(CharityFaultTypeContentEntity::getCfTypeId).collect(Collectors.toList());
            // 获取过失类型
            List<CharityFaultTypeEntity> charityFaultTypeEntities = charityFaultTypeRepository.findByCfTypeIdIn(cfTypeIds);
            Map<Integer, CharityFaultTypeEntity> charityFaultTypeEntityMap = charityFaultTypeEntities.stream().collect(Collectors.toMap(CharityFaultTypeEntity::getCfTypeId, Function.identity()));
            List<CharityFaultTypeContent> charityFaultTypeContents = charityFaultTypeContentEntities.stream().map(charityFaultTypeContentEntity -> {
                CharityFaultTypeContent charityFaultTypeContent = new CharityFaultTypeContent();
                BeanUtils.copyProperties(charityFaultTypeContentEntity, charityFaultTypeContent);
                CharityFaultTypeEntity charityFaultTypeEntity = charityFaultTypeEntityMap.get(charityFaultTypeContentEntity.getCfTypeId());
                charityFaultTypeContent.setTypeName(charityFaultTypeEntity.getTypeName());
                return charityFaultTypeContent;
            }).collect(Collectors.toList());
            return new PageImpl<>(charityFaultTypeContents, PageRequest.of(0, charityFaultTypeContents.size() + 1), charityFaultTypeContents.size());
        } else {
            Page<CharityFaultTypeContentEntity> charityFaultTypeContentEntities;
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            if (cfTypeId != null) {
                charityFaultTypeContentEntities = charityFaultTypeContentRepository.findByCfTypeId(cfTypeId, pageRequest);
            } else {
                charityFaultTypeContentEntities = charityFaultTypeContentRepository.findAll(pageRequest);
            }
            List<Integer> cfTypeIds = charityFaultTypeContentEntities.stream().map(CharityFaultTypeContentEntity::getCfTypeId).collect(Collectors.toList());
            // 获取过失类型
            List<CharityFaultTypeEntity> charityFaultTypeEntities = charityFaultTypeRepository.findByCfTypeIdIn(cfTypeIds);
            Map<Integer, CharityFaultTypeEntity> charityFaultTypeEntityMap = charityFaultTypeEntities.stream().collect(Collectors.toMap(CharityFaultTypeEntity::getCfTypeId, Function.identity()));
            List<CharityFaultTypeContent> charityFaultTypeContents = charityFaultTypeContentEntities.stream().map(charityFaultTypeContentEntity -> {
                CharityFaultTypeContent charityFaultTypeContent = new CharityFaultTypeContent();
                BeanUtils.copyProperties(charityFaultTypeContentEntity, charityFaultTypeContent);
                CharityFaultTypeEntity charityFaultTypeEntity = charityFaultTypeEntityMap.get(charityFaultTypeContentEntity.getCfTypeId());
                charityFaultTypeContent.setTypeName(charityFaultTypeEntity.getTypeName());
                return charityFaultTypeContent;
            }).collect(Collectors.toList());
            return new PageImpl<>(charityFaultTypeContents, pageRequest, charityFaultTypeContentEntities.getTotalElements());
        }
    }

    @Override
    public CharityFaultTypeContentEntity addCharityFaultTypeContent(CharityFaultTypeContentEntity charityFaultTypeContentEntity) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        charityFaultTypeContentEntity.setCreateUserName(userEntity.getLoginName());
        return charityFaultTypeContentRepository.save(charityFaultTypeContentEntity);
    }

    @Override
    public CharityFaultTypeContentEntity modifyCharityFaultTypeContent(CharityFaultTypeContentEntity charityFaultTypeContentEntity) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        CharityFaultTypeContentEntity entity = charityFaultTypeContentRepository.findById(charityFaultTypeContentEntity.getId());
        if (entity != null) {
            BeanUtils.copyProperties(charityFaultTypeContentEntity, entity);
            entity.setUpdateUserName(userEntity.getLoginName());
            charityFaultTypeContentRepository.save(entity);
        }
        return null;
    }

    @Override
    public void deleteCharityFaultTypeContent(Integer id) {
        CharityFaultTypeContentEntity charityFaultTypeContentEntity = charityFaultTypeContentRepository.findById(id);
        if (charityFaultTypeContentEntity != null) {
            charityFaultTypeContentRepository.delete(charityFaultTypeContentEntity);
        }
    }
}
