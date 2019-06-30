package org.flightythought.smile.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.flightythought.smile.admin.bean.HotSearchInfo;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.HotSearchEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.HotSearchRepository;
import org.flightythought.smile.admin.dto.HotSearchDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.HotSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotSearchServiceImpl implements HotSearchService {
    @Autowired
    private PlatformUtils       platformUtils;
    @Autowired
    private HotSearchRepository hotSearchRepository;

    @Override
    public void addHotSearch(HotSearchDTO hotSearchDTO) {
        HotSearchEntity hotSearchEntity = new HotSearchEntity();
        BeanUtils.copyProperties(hotSearchDTO, hotSearchEntity);
        // 登陆用户
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 创建者
        hotSearchEntity.setCreateUserName(userEntity.getLoginName());
        // 保存
        hotSearchRepository.save(hotSearchEntity);
    }

    @Override
    public List<HotSearchInfo> getHotSearchList() {
        List<HotSearchEntity> list = hotSearchRepository.findAll();
        return list.stream().map(e -> {
            HotSearchInfo hotSearchInfo = new HotSearchInfo();
            hotSearchInfo.setId(e.getId());
            hotSearchInfo.setCode(e.getCode());
            hotSearchInfo.setDescription(e.getDescription());
            return hotSearchInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public HotSearchInfo getHotSearch(Integer id) {
        HotSearchEntity entity = hotSearchRepository.findById(id);
        HotSearchInfo hotSearchInfo = new HotSearchInfo();
        hotSearchInfo.setId(entity.getId());
        hotSearchInfo.setCode(entity.getCode());
        hotSearchInfo.setDescription(entity.getDescription());
        return hotSearchInfo;
    }

    @Override
    public void updateHotSearch(HotSearchDTO hotSearchDTO) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        // ID
        Integer id = hotSearchDTO.getId();
        if (id == null) {
            throw new FlightyThoughtException("请传递Id");
        }
        HotSearchEntity entity = hotSearchRepository.findById(id);
        if (entity != null) {
            entity.setCode(hotSearchDTO.getCode());
            entity.setDescription(hotSearchDTO.getDescription());
            // 更新者
            entity.setUpdateUserName(userEntity.getLoginName());
            hotSearchRepository.save(entity);
        }
    }

    @Override
    public void deleteHotSearch(Integer id) {
        HotSearchEntity entity = hotSearchRepository.findById(id);
        if (entity != null) {
            hotSearchRepository.delete(entity);
        }
    }

}
