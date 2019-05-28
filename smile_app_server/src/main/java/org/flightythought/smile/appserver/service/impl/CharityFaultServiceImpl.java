package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.common.PushCodeEnum;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.JPushUtils;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.*;
import org.flightythought.smile.appserver.dto.*;
import org.flightythought.smile.appserver.service.CharityFaultService;
import org.flightythought.smile.appserver.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    @Autowired
    private CharityFaultMessageRepository charityFaultMessageRepository;
    @Autowired
    private UserCharityFaultMessageLikeRepository userCharityFaultMessageLikeRepository;
    @Autowired
    private JPushUtils jPushUtils;

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
        // 是否隐藏
        userCharityFaultRecord.setHidden(charityFaultRecordDTO.getHidden() == null ? false : charityFaultRecordDTO.getHidden());
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
    public Page<UserCharityFaultRecord> getCharityFaults(CharityFaultQueryDTO charityFaultQueryDTO) {
        PageRequest pageRequest = PageRequest.of(charityFaultQueryDTO.getPageNumber() - 1, charityFaultQueryDTO.getPageSize());
        Integer cfTypeId = charityFaultQueryDTO.getCfTypeId();
        Page<UserCharityFaultRecordEntity> userCharityFaultRecordEntities;
        Long userId = charityFaultQueryDTO.getUserId();
        if (cfTypeId == null) {
            if (userId != null) {
                userCharityFaultRecordEntities = userCharityFaultRecordRepository.findByUserIdAndHiddenOrderByCreateTimeDesc(userId, false, pageRequest);
            } else {
                userCharityFaultRecordEntities = userCharityFaultRecordRepository.findByHiddenOrderByCreateTimeDesc(false, pageRequest);
            }
        } else {
            if (userId != null) {
                userCharityFaultRecordEntities = userCharityFaultRecordRepository.findByUserIdAndCfTypeIdAndHiddenOrderByCreateTimeDesc(userId, cfTypeId, false, pageRequest);
            } else {
                userCharityFaultRecordEntities = userCharityFaultRecordRepository.
                        findByCfTypeIdAndHiddenOrderByCreateTimeDesc(cfTypeId, false, pageRequest);
            }
        }
        List<UserCharityFaultRecord> userCharityFaultRecords = userCharityFaultRecordEntities.stream()
                .sorted(Comparator.comparingLong(value -> value.getCreateTime().toInstant(ZoneOffset.UTC).toEpochMilli()))
                .map(this::getUserCharityFaultRecord).collect(Collectors.toList());
        return new PageImpl<>(userCharityFaultRecords, pageRequest, userCharityFaultRecordEntities.getTotalElements());
    }

    @Override
    public Page<UserCharityFaultRecord> getMyCharityFaults(PageFilterDTO pageFilterDTO) {
        PageRequest pageRequest = PageRequest.of(pageFilterDTO.getPageNumber() - 1, pageFilterDTO.getPageSize());
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        Page<UserCharityFaultRecordEntity> userCharityFaultRecordEntities = userCharityFaultRecordRepository.findByUserIdOrderByCreateTimeDesc(userId, pageRequest);
        List<UserCharityFaultRecord> userCharityFaultRecords = userCharityFaultRecordEntities.stream()
                .map(this::getUserCharityFaultRecord).collect(Collectors.toList());
        return new PageImpl<>(userCharityFaultRecords, pageRequest, userCharityFaultRecordEntities.getTotalElements());
    }

    @Override
    public UserCharityFaultRecord getUserCharityFaultRecord(UserCharityFaultRecordEntity userCharityFaultRecordEntity) {
        if (userCharityFaultRecordEntity != null) {
            UserCharityFaultRecord result = new UserCharityFaultRecord();
            BeanUtils.copyProperties(userCharityFaultRecordEntity, result);
            UserInfo userInfo = userService.getUserInfo(userCharityFaultRecordEntity.getUserEntity());
            result.setUserInfo(userInfo);
            List<ImagesEntity> imagesEntities = userCharityFaultRecordEntity.getImages();
            if (imagesEntities != null && imagesEntities.size() > 0) {
                String domainPort = platformUtils.getDomainPort();
                List<ImageInfo> imageInfos = imagesEntities.stream()
                        .map(imagesEntity -> platformUtils.getImageInfo(imagesEntity, domainPort))
                        .collect(Collectors.toList());
                result.setImages(imageInfos);
            } else {
                result.setImages(new ArrayList<>());
            }
            return result;
        }
        return null;
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
                    ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                    imageInfos.add(imageInfo);
                });
            }
            UserCharityFaultRecord result = new UserCharityFaultRecord(charityFaultRecordEntity, imageInfos);
            UserEntity userEntity = charityFaultRecordEntity.getUserEntity();
            UserInfo userInfo = userService.getUserInfo(userEntity);
            result.setUserInfo(userInfo);
            return result;
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

    @Override
    public CharityFaultMessageSimple addCharityFaultMessage(CharityFaultMessageDTO charityFaultMessageDTO) {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        CharityFaultMessageEntity charityFaultMessageEntity = new CharityFaultMessageEntity();
        // 动态明细ID
        charityFaultMessageEntity.setCharityFaultId(charityFaultMessageDTO.getCharityFaultId());
        // 评论内容
        charityFaultMessageEntity.setMessage(charityFaultMessageDTO.getMessage());
        // 父级ID
        charityFaultMessageEntity.setParentId(charityFaultMessageDTO.getParentId());
        // 创建者用户ID
        charityFaultMessageEntity.setFromUserId(charityFaultMessageDTO.getFromUserId());
        // 发送给用户ID
        charityFaultMessageEntity.setToUserId(charityFaultMessageDTO.getToUserId());
        // 点赞个数
        charityFaultMessageEntity.setLikeNum(0);
        // 接受者用户是否查看
        charityFaultMessageEntity.setRead(false);
        // 创建者
        charityFaultMessageEntity.setCreateUserName(userEntity.getId() + "");
        // 评论标志类型
        charityFaultMessageEntity.setFlagType(charityFaultMessageDTO.getFlagType());
        // 保存
        charityFaultMessageEntity = charityFaultMessageRepository.save(charityFaultMessageEntity);
        CharityFaultMessageSimple result = new CharityFaultMessageSimple();
        BeanUtils.copyProperties(charityFaultMessageEntity, result);
        // 评论自己发布的或自己评论自己不推送
        if (!userEntity.getId().equals(charityFaultMessageDTO.getToUserId()) && !charityFaultMessageDTO.getToUserId().equals(charityFaultMessageDTO.getFromUserId())) {
            // 推送信息
            PushMessage<CharityFaultMessageSimple> pushMessage = new PushMessage<>();
            pushMessage.setData(result);
            pushMessage.setCode(PushCodeEnum.CHARITY_FAULT_MESSAGE.getMessage());
            pushMessage.setType(Constants.NOTICE_MESSAGE);
            pushMessage.setMessage("您有一条新评论");
            pushMessage.setTitle("您有一条新评论");
            jPushUtils.pushData(pushMessage, charityFaultMessageDTO.getToUserId());
        }
        return result;
    }

    @Override
    public void likeCharityFaultMessage(Integer messageId) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        // 获取爱心养生明细评论
        CharityFaultMessageEntity charityFaultMessageEntity = charityFaultMessageRepository.findById(messageId);
        if (charityFaultMessageEntity == null) {
            throw new FlightyThoughtException("没有找到对应的评论信息");
        }
        // 查询判断当前用户有没有点赞
        UserCharityFaultMessageLikeEntity likeEntity = userCharityFaultMessageLikeRepository.findByUserIdAndMessageId(userId, messageId);
        if (likeEntity != null) {
            charityFaultMessageEntity.setLikeNum(charityFaultMessageEntity.getLikeNum() - 1);
            // 取消点赞
            userCharityFaultMessageLikeRepository.delete(likeEntity);
        } else {
            charityFaultMessageEntity.setLikeNum(charityFaultMessageEntity.getLikeNum() == null ? 1 : charityFaultMessageEntity.getLikeNum() + 1);
            likeEntity = new UserCharityFaultMessageLikeEntity();
            likeEntity.setUserId(userId);
            likeEntity.setMessageId(messageId);
            userCharityFaultMessageLikeRepository.save(likeEntity);
            // 点赞自己的评论不推送
            if (userId != charityFaultMessageEntity.getFromUserId()) {
                // 推送消息
                List<CharityFaultMessageEntity> charityFaultMessageEntities = new ArrayList<>();
                charityFaultMessageEntities.add(charityFaultMessageEntity);
                List<CharityFaultMessageSimple> charityFaultMessageSimples = new ArrayList<>();
                getCharityFaultMessageSimple(charityFaultMessageEntities, charityFaultMessageSimples);
                CharityFaultMessageSimple charityFaultMessageSimple = charityFaultMessageSimples.get(0);
                PushMessage<CharityFaultMessageSimple> pushMessage = new PushMessage<>();
                pushMessage.setData(charityFaultMessageSimple);
                pushMessage.setMessage("有人点赞您发表的评论");
                pushMessage.setTitle("有人点赞您发表的评论");
                pushMessage.setType(Constants.NOTICE_LIKE);
                pushMessage.setCode(PushCodeEnum.CHARITY_FAULT_MESSAGE_LIKE.getMessage());
                jPushUtils.pushData(pushMessage, charityFaultMessageEntity.getFromUserId());
            }
        }
        charityFaultMessageRepository.save(charityFaultMessageEntity);
    }

    @Override
    @Transactional
    public Page<CharityFaultMessageSimple> getCharityFaultMessage(CharityFaultMessageQueryDTO charityFaultMessageQueryDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        Integer charityFaultId = charityFaultMessageQueryDTO.getCharityFaultId();
        Pageable pageable = PageRequest.of(charityFaultMessageQueryDTO.getPageNumber() - 1, charityFaultMessageQueryDTO.getPageSize());
        Page<CharityFaultMessageEntity> charityFaultMessageEntities = charityFaultMessageRepository.findByCharityFaultIdAndParentIdIsNullOrderByCreateTimeDesc(charityFaultId, pageable);
        List<CharityFaultMessageSimple> result = new ArrayList<>();
        getCharityFaultMessageSimple(charityFaultMessageEntities.getContent(), result);
        return new PageImpl<>(result, charityFaultMessageEntities.getPageable(), charityFaultMessageEntities.getTotalElements());
    }

    @Override
    @Transactional
    public Page<CharityFaultMessageSimple> getCharityFaultMessageInfo(CharityFaultMessageQueryDTO charityFaultMessageQueryDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        Integer charityFaultId = charityFaultMessageQueryDTO.getCharityFaultId();
        Pageable pageable = PageRequest.of(charityFaultMessageQueryDTO.getPageNumber() - 1, charityFaultMessageQueryDTO.getPageSize());
        Integer messageId = charityFaultMessageQueryDTO.getMessageId();
        Page<CharityFaultMessageEntity> charityFaultMessageEntities = charityFaultMessageRepository.findByCharityFaultIdAndParentIdOrderByCreateTimeDesc(charityFaultId, messageId, pageable);
        List<CharityFaultMessageSimple> result = new ArrayList<>();
        getCharityFaultMessageSimple(charityFaultMessageEntities.getContent(), result);
        return new PageImpl<>(result, charityFaultMessageEntities.getPageable(), charityFaultMessageEntities.getTotalElements());
    }

    public void getCharityFaultMessageSimple(List<CharityFaultMessageEntity> charityFaultMessageEntities, List<CharityFaultMessageSimple> result) {
        if (charityFaultMessageEntities == null || charityFaultMessageEntities.size() == 0) {
            return;
        }
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        List<Integer> messageIds = charityFaultMessageEntities.stream().map(CharityFaultMessageEntity::getId).collect(Collectors.toList());
        // 获取用户点赞的信息集合
        List<UserCharityFaultMessageLikeEntity> userToMessageLikeEntities = userCharityFaultMessageLikeRepository.findByUserIdAndMessageIdIn(userId, messageIds);
        Map<Integer, UserCharityFaultMessageLikeEntity> likeEntityMap = userToMessageLikeEntities.stream().collect(Collectors.toMap(UserCharityFaultMessageLikeEntity::getMessageId, Function.identity()));
        charityFaultMessageEntities.forEach(charityFaultMessageEntity -> {
            CharityFaultMessageSimple charityFaultMessageSimple = new CharityFaultMessageSimple();
            result.add(charityFaultMessageSimple);
            // 主键ID
            charityFaultMessageSimple.setId(charityFaultMessageEntity.getId());
            // 行善过失ID
            charityFaultMessageSimple.setCharityFaultId(charityFaultMessageEntity.getCharityFaultId());
            // 评论内容
            charityFaultMessageSimple.setMessage(charityFaultMessageEntity.getMessage());
            // 父级ID
            charityFaultMessageSimple.setParentId(charityFaultMessageEntity.getParentId());
            // 发送者
            UserEntity fromUserEntity = charityFaultMessageEntity.getFromUser();
            if (fromUserEntity != null) {
                UserInfo fromUser = userService.getUserInfo(fromUserEntity);
                charityFaultMessageSimple.setFromUser(fromUser);
            }
            // 接收者
            UserEntity toUserEntity = charityFaultMessageEntity.getToUser();
            if (toUserEntity != null) {
                UserInfo toUser = userService.getUserInfo(toUserEntity);
                charityFaultMessageSimple.setToUser(toUser);
            }
            // 接收者是否阅读
            charityFaultMessageSimple.setRead(charityFaultMessageEntity.getRead());
            // 点赞个数
            charityFaultMessageSimple.setLikeNum(charityFaultMessageEntity.getLikeNum());
            // TODO 当前用户是否点赞
            if (likeEntityMap.get(charityFaultMessageEntity.getId()) != null) {
                charityFaultMessageSimple.setLike(true);
            } else {
                charityFaultMessageSimple.setLike(false);
            }
            // flagType
            charityFaultMessageSimple.setFlagType(charityFaultMessageEntity.getFlagType());
            // 创建时间
            charityFaultMessageSimple.setCreateTime(charityFaultMessageEntity.getCreateTime());
            // 判断是否有子集
            List<CharityFaultMessageEntity> childDetailMessageEntities = charityFaultMessageEntity.getChildMessage();
            if (childDetailMessageEntities != null && childDetailMessageEntities.size() > 0) {
                List<CharityFaultMessageSimple> child = new ArrayList<>();
                charityFaultMessageSimple.setChildMessage(child);
                getCharityFaultMessageSimple(childDetailMessageEntities, child);
            }
        });
    }
}
