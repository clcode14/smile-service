package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.CharityAndFault;
import org.flightythought.smile.appserver.bean.CharityFaultTypeContentSimple;
import org.flightythought.smile.appserver.bean.CharityFaultTypeSimple;
import org.flightythought.smile.appserver.database.entity.CharityFaultTypeContentEntity;
import org.flightythought.smile.appserver.database.entity.CharityFaultTypeEntity;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.CharityFaultTypeRepository;
import org.flightythought.smile.appserver.database.repository.UserCharityFaultRecordRepository;
import org.flightythought.smile.appserver.dto.CharityFaultRecordDTO;
import org.flightythought.smile.appserver.service.CharityFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharityFaultServiceImpl implements CharityFaultService {

    @Autowired
    private CharityFaultTypeRepository charityFaultTypeRepository;
    @Autowired
    private UserCharityFaultRecordRepository userCharityFaultRecordRepository;

    @Override
    @Transactional
    public CharityAndFault getCharityAndFault(Integer cfTypeId) {
        List<CharityFaultTypeEntity> charityFaultTypeEntities;
        if (cfTypeId == null) {
            charityFaultTypeEntities = charityFaultTypeRepository.findAll();
        } else {
            charityFaultTypeEntities = charityFaultTypeRepository.findByCfTypeId(cfTypeId);
        }
        CharityAndFault result = new CharityAndFault();
        List<CharityFaultTypeSimple> charity = result.getCharity();
        List<CharityFaultTypeSimple> fault = result.getFault();
        charityFaultTypeEntities.forEach(charityFaultTypeEntity -> {
            CharityFaultTypeSimple charityFaultTypeSimple = new CharityFaultTypeSimple();
            // 行善过失类型ID
            charityFaultTypeSimple.setCfTypeId(charityFaultTypeEntity.getCfTypeId());
            // 类型名称
            charityFaultTypeSimple.setTypeName(charityFaultTypeEntity.getTypeName());
            // 行善过失类型，0：行善、1：过失
            charityFaultTypeSimple.setType(charityFaultTypeEntity.getType());
            // 行善过失内容
            List<CharityFaultTypeContentEntity> charityFaultTypeContentEntities = charityFaultTypeEntity.getCharityFaultTypeContentEntities();
            List<CharityFaultTypeContentSimple> charityFaultTypeContentSimples = new ArrayList<>();
            charityFaultTypeContentEntities.forEach(charityFaultTypeContentEntity -> {
                CharityFaultTypeContentSimple charityFaultTypeContentSimple = new CharityFaultTypeContentSimple();
                // 内容ID
                charityFaultTypeContentSimple.setContentId(charityFaultTypeContentEntity.getId());
                // 行善过失ID
                charityFaultTypeContentSimple.setCfTypeId(charityFaultTypeContentEntity.getCfTypeId());
                // 内容
                charityFaultTypeContentSimple.setContent(charityFaultTypeContentEntity.getContent());
                charityFaultTypeContentSimples.add(charityFaultTypeContentSimple);
            });
            charityFaultTypeSimple.setContents(charityFaultTypeContentSimples);
            switch (charityFaultTypeEntity.getType()) {
                case 0:
                    // 善行
                    charity.add(charityFaultTypeSimple);
                    break;
                case 1:
                    // 过失
                    fault.add(charityFaultTypeSimple);
                    break;
                default:
                    break;
            }
        });
        return result;
    }

    @Override
    public UserCharityFaultRecordEntity addCharityFaultRecord(CharityFaultRecordDTO charityFaultRecordDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCharityFaultRecordEntity userCharityFaultRecord = new UserCharityFaultRecordEntity();
        // 用户ID
        userCharityFaultRecord.setUserId(userEntity.getId());
        userCharityFaultRecord.setCreateUserName(userEntity.getId().toString());
        // 类型
        userCharityFaultRecord.setType(charityFaultRecordDTO.getType());
        // 内容记录
        userCharityFaultRecord.setContent(charityFaultRecordDTO.getContent());
        // 行善日期
        userCharityFaultRecord.setCharityTime(charityFaultRecordDTO.getCharityTime());
        // 行善类型ID
        userCharityFaultRecord.setCfTypeId(charityFaultRecordDTO.getCfTypeId());
        // 行善类型对应的内容ID
        userCharityFaultRecord.setTypeContentId(charityFaultRecordDTO.getTypeContentId());
        // 捐款金额
        userCharityFaultRecord.setDonateAmount(charityFaultRecordDTO.getDonateAmount());
        // 物资详情
        userCharityFaultRecord.setMaterialDetails(charityFaultRecordDTO.getMaterialDetails());
        // 经度
        userCharityFaultRecord.setLongitude(charityFaultRecordDTO.getLongitude());
        // 维度
        userCharityFaultRecord.setLatitude(charityFaultRecordDTO.getLatitude());
        // 地址
        userCharityFaultRecord.setAddress(charityFaultRecordDTO.getAddress());
        userCharityFaultRecordRepository.save(userCharityFaultRecord);
        return userCharityFaultRecord;
    }
}
