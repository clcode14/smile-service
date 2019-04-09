package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.HealthNormTypeRepository;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.JourneyHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class JourneyHealthServiceImpl implements JourneyHealthService {
    @Autowired
    private HealthNormTypeRepository healthNormTypeRepository;

    @Override
    public Page<HealthNormTypeEntity> getHealthNormType(Integer pageSize, Integer pageNumber) {
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            // 分页查询
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            return healthNormTypeRepository.findAll(pageRequest);
        } else {
            List<HealthNormTypeEntity> healthNormTypeEntities = healthNormTypeRepository.findAll();
            PageImpl<HealthNormTypeEntity> result = new PageImpl<>(healthNormTypeEntities, PageRequest.of(0, healthNormTypeEntities.size()), healthNormTypeEntities.size());
            return result;
        }
    }

    @Override
    public HealthNormTypeEntity saveOrUpdateHealthNormType(HealthNormTypeEntity healthNormTypeEntity, HttpSession session) throws FlightyThoughtException {
        // 获取当前登录用户
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 判断新增还是修改
        if (healthNormTypeEntity.getNormTypeId() != null) {
            HealthNormTypeEntity entity = healthNormTypeRepository.findByNormTypeId(healthNormTypeEntity.getNormTypeId());
            if (entity == null) {
                throw new FlightyThoughtException("修改失败，normTypeId=" + healthNormTypeEntity.getNormTypeId() + "，没找到对应的数据");
            } else {
                entity.setNormName(healthNormTypeEntity.getNormName());
                entity.setNormNumber(healthNormTypeEntity.getNormNumber());
                entity.setUpdateUserName(sysUserEntity.getLoginName());
                return healthNormTypeRepository.save(entity);
            }
        }
        healthNormTypeEntity.setCreateUserName(sysUserEntity.getLoginName());
        return healthNormTypeRepository.save(healthNormTypeEntity);
    }

    @Override
    public void deleteHealthNormType(Integer normTypeId) {
        HealthNormTypeEntity entity = healthNormTypeRepository.findByNormTypeId(normTypeId);
        if (entity != null) {
            healthNormTypeRepository.delete(entity);
        }
    }
}
