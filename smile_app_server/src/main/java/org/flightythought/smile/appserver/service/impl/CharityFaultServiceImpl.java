package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.CharityFaultRecordImageRepository;
import org.flightythought.smile.appserver.database.repository.CharityFaultTypeRepository;
import org.flightythought.smile.appserver.database.repository.ImagesRepository;
import org.flightythought.smile.appserver.database.repository.UserCharityFaultRecordRepository;
import org.flightythought.smile.appserver.dto.CharityFaultRecordDTO;
import org.flightythought.smile.appserver.dto.FileImageDTO;
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
    @Autowired
    private CharityFaultRecordImageRepository charityFaultRecordImageRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private PlatformUtils platformUtils;

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
    @Transactional
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
        // 获取上传图片
        List<FileImageDTO> images = charityFaultRecordDTO.getImages();
        List<CharityFaultRecordImageEntity> charityFaultRecordImageEntities = new ArrayList<>();
        images.forEach(fileImageDTO -> {
            CharityFaultRecordImageEntity charityFaultRecordImageEntity = new CharityFaultRecordImageEntity();
            charityFaultRecordImageEntity.setImageId(fileImageDTO.getImageId());
            charityFaultRecordImageEntity.setCharityFaultRecordId(userCharityFaultRecord.getId());
            charityFaultRecordImageEntities.add(charityFaultRecordImageEntity);
        });
        charityFaultRecordImageRepository.saveAll(charityFaultRecordImageEntities);
        return userCharityFaultRecord;
    }

    @Override
    public UserCharityFaultRecord getUserCharityFaultRecord(Integer id) {
        // 获取用户善行过失记录
        UserCharityFaultRecordEntity charityFaultRecordEntity = userCharityFaultRecordRepository.findById(id);
        if (charityFaultRecordEntity != null) {
            // 根据ID获取图片关联数据
            List<CharityFaultRecordImageEntity> charityFaultRecordImageEntities = charityFaultRecordImageRepository.findByCharityFaultRecordId(charityFaultRecordEntity.getId());
            List<Integer> imagesId = new ArrayList<>();
            charityFaultRecordImageEntities.forEach(charityFaultRecordImageEntity -> imagesId.add(charityFaultRecordImageEntity.getImageId()));
            // 获取图片
            List<ImagesEntity> imagesEntities = imagesRepository.findByIdIn(imagesId);
            List<ImageInfo> imageInfos = new ArrayList<>();
            String domainPort = platformUtils.getDomainPort();
            if (imagesEntities != null) {
                imagesEntities.forEach(imagesEntity -> {
                    ImageInfo imageInfo = new ImageInfo();
                    String url = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                    imageInfo.setId(imagesEntity.getId());
                    imageInfo.setName(imagesEntity.getFileName());
                    imageInfo.setSize(imagesEntity.getSize());
                    imageInfo.setUrl(url);
                    imageInfos.add(imageInfo);
                });
            }
            return new UserCharityFaultRecord(charityFaultRecordEntity, imageInfos);
        }
        return null;
    }
}
