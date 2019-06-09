package org.flightythought.smile.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.flightythought.smile.admin.bean.EncourageInfo;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.EncourageEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.EncourageRepository;
import org.flightythought.smile.admin.dto.EncourageDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.EncourageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cl47872
 * @version $Id: EncourageServiceImpl.java, v 0.1 Jun 8, 2019 10:11:51 PM cl47872 Exp $
 */
@Service
public class EncourageServiceImpl implements EncourageService {
    @Autowired
    private PlatformUtils       platformUtils;
    @Autowired
    private EncourageRepository encourageRepository;

    @Override
    public void addEncourage(EncourageDTO encourageDTO) {
        EncourageEntity encourageEntity = new EncourageEntity();
        BeanUtils.copyProperties(encourageDTO, encourageEntity);
        // 登陆用户
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 创建者
        encourageEntity.setCreateUserName(userEntity.getLoginName());
        // 保存
        encourageRepository.save(encourageEntity);
    }

    @Override
    public List<EncourageInfo> getEncourageList() {
        List<EncourageEntity> list = encourageRepository.findAll();
        return list.stream().map(e -> {
            EncourageInfo encourageInfo = new EncourageInfo();
            encourageInfo.setId(e.getId());
            encourageInfo.setCode(e.getCode());
            encourageInfo.setDescription(e.getDescription());
            return encourageInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public EncourageInfo getEncourage(Integer id) {
        EncourageEntity entity = encourageRepository.findById(id);
        EncourageInfo encourageInfo = new EncourageInfo();
        encourageInfo.setId(entity.getId());
        encourageInfo.setCode(entity.getCode());
        encourageInfo.setDescription(entity.getDescription());
        return encourageInfo;
    }

    @Override
    public void updateEncourage(EncourageDTO encourageDTO) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        // ID
        Integer id = encourageDTO.getId();
        if (id == null) {
            throw new FlightyThoughtException("请传递Id");
        }
        EncourageEntity entity = encourageRepository.findById(id);
        if (entity != null) {
            entity.setCode(encourageDTO.getCode());
            entity.setDescription(encourageDTO.getDescription());
            // 更新者
            entity.setUpdateUserName(userEntity.getLoginName());
            // 更新商品
            encourageRepository.save(entity);
        }
    }

    @Override
    public void deleteEncourage(Integer id) {
        EncourageEntity entity = encourageRepository.findById(id);
        if (entity != null) {
            encourageRepository.delete(entity);
        }
    }
}
