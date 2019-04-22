package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.*;
import org.flightythought.smile.appserver.dto.CharityFaultRecordDTO;
import org.flightythought.smile.appserver.dto.FileImageDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.CharityFaultService;
import org.flightythought.smile.appserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    private UserCharityFaultIntegralRepository userCharityFaultIntegralRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public CharityAndFault getCharityAndFault(Integer cfTypeId) {
        List<CharityFaultTypeEntity> charityFaultTypeEntities;
        if (cfTypeId == null) {
            charityFaultTypeEntities = charityFaultTypeRepository.findAll();
        } else {
            charityFaultTypeEntities = new ArrayList<>();
            charityFaultTypeEntities.add(charityFaultTypeRepository.findByCfTypeId(cfTypeId));
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
            // 行善过失积分
            charityFaultTypeSimple.setIntegral(charityFaultTypeEntity.getIntegral());
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
        // 获取行善过失类型
        CharityFaultTypeEntity charityFaultTypeEntity = charityFaultTypeRepository.findByCfTypeId(charityFaultRecordDTO.getCfTypeId());
        if (charityFaultTypeEntity == null) {
            throw new FlightyThoughtException("传递的行善过失类型ID无效！");
        }
        UserCharityFaultRecordEntity userCharityFaultRecord = new UserCharityFaultRecordEntity();
        // 用户ID
        userCharityFaultRecord.setUserId(userEntity.getId());
        userCharityFaultRecord.setCreateUserName(userEntity.getId().toString());
        // 类型
        userCharityFaultRecord.setType(charityFaultRecordDTO.getType());
        // 内容记录
        userCharityFaultRecord.setContent(charityFaultRecordDTO.getContent());
        // 行善日期 开始时间
        userCharityFaultRecord.setCharityTimeStart(charityFaultRecordDTO.getCharityTimeStart());
        // 行善日记 结束时间
        userCharityFaultRecord.setCharityTimeEnd(charityFaultRecordDTO.getCharityTimeEnd());
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
        // 获取用户积分
        UserCharityFaultIntegralEntity userCharityFaultIntegralEntity = userCharityFaultIntegralRepository.findByUserId(userEntity.getId());
        if (userCharityFaultIntegralEntity == null) {
            userCharityFaultIntegralEntity = new UserCharityFaultIntegralEntity(userEntity.getId());
        } else {
            userCharityFaultIntegralEntity.setUpdateUserName(userEntity.getId() + "");
        }
        // 判断新增的记录是行善记录还是过失记录
        if (charityFaultRecordDTO.getType() == 0) {
            // 行善记录
            userCharityFaultIntegralEntity.setCharityCount(userCharityFaultIntegralEntity.getCharityCount() + 1);
        } else if (charityFaultRecordDTO.getType() == 1) {
            // 过失记录
            userCharityFaultIntegralEntity.setFaultCount(userCharityFaultIntegralEntity.getFaultCount() + 1);
        }
        // 分数计算
        userCharityFaultIntegralEntity.setScore(userCharityFaultIntegralEntity.getScore() + charityFaultTypeEntity.getIntegral());
        // 保存用户分数行善过失记录
        userCharityFaultIntegralRepository.save(userCharityFaultIntegralEntity);
        // 获取上传图片
        List<FileImageDTO> images = charityFaultRecordDTO.getImages();
        List<CharityFaultRecordImageEntity> charityFaultRecordImageEntities = new ArrayList<>();
        if (images != null && images.size() > 0) {
            images.forEach(fileImageDTO -> {
                CharityFaultRecordImageEntity charityFaultRecordImageEntity = new CharityFaultRecordImageEntity();
                charityFaultRecordImageEntity.setImageId(fileImageDTO.getId());
                charityFaultRecordImageEntity.setCharityFaultRecordId(userCharityFaultRecord.getId());
                charityFaultRecordImageEntities.add(charityFaultRecordImageEntity);
            });
        }
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
                    String url = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
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

    @Override
    @Transactional
    public Page<CharityFaultStatistics> getCharityFaultInfoOrRanking(PageFilterDTO pageFilterDTO, Long userId) {
        // 获取分页查询
        Integer pageSize = pageFilterDTO.getPageSize();
        Integer pageNumber = pageFilterDTO.getPageNumber();
        PageRequest pageRequest;
        Long total;
        List<UserCharityFaultIntegralEntity> userCharityFaultIntegralEntities;
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            if (userId != null) {
                UserCharityFaultIntegralEntity exampleEntity = new UserCharityFaultIntegralEntity();
                exampleEntity.setUserId(userId);
                userCharityFaultIntegralEntities = userCharityFaultIntegralRepository.findAll(Example.of(exampleEntity), sort);
            } else {
                userCharityFaultIntegralEntities = userCharityFaultIntegralRepository.findAll(sort);
            }
            pageRequest = PageRequest.of(0, userCharityFaultIntegralEntities.size() + 1);
            total = (long) userCharityFaultIntegralEntities.size();
        } else {
            pageRequest = PageRequest.of(pageNumber, pageSize, sort);
            Page<UserCharityFaultIntegralEntity> userCharityFaultIntegralEntityPage;
            if (userId != null) {
                UserCharityFaultIntegralEntity exampleEntity = new UserCharityFaultIntegralEntity();
                exampleEntity.setUserId(userId);
                userCharityFaultIntegralEntityPage = userCharityFaultIntegralRepository.findAll(Example.of(exampleEntity), pageRequest);
            } else {
                userCharityFaultIntegralEntityPage = userCharityFaultIntegralRepository.findAll(pageRequest);
            }
            userCharityFaultIntegralEntities = userCharityFaultIntegralEntityPage.getContent();
            total = userCharityFaultIntegralEntityPage.getTotalElements();
        }
        List<CharityFaultStatistics> charityFaultStatisticsList = new ArrayList<>();
        userCharityFaultIntegralEntities.forEach(charityFaultIntegralEntity -> {
            CharityFaultStatistics charityFaultStatistics = new CharityFaultStatistics();
            // 行善次数
            charityFaultStatistics.setCharityCount(charityFaultIntegralEntity.getCharityCount());
            // 过失次数
            charityFaultStatistics.setFaultCount(charityFaultIntegralEntity.getFaultCount());
            // 分数
            charityFaultStatistics.setScore(charityFaultIntegralEntity.getScore());
            // 用户信息
            UserEntity userEntity = charityFaultIntegralEntity.getUser();
            if (userEntity != null) {
                UserInfo userInfo = userService.getUserInfo(userEntity);
                charityFaultStatistics.setUser(userInfo);
            }
            charityFaultStatisticsList.add(charityFaultStatistics);
        });
        return new PageImpl<>(charityFaultStatisticsList, pageRequest, total);
    }
}
