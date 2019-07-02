package org.flightythought.smile.appserver.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.flightythought.smile.appserver.bean.DynamicDetailMessageSimple;
import org.flightythought.smile.appserver.bean.DynamicDetailSimple;
import org.flightythought.smile.appserver.bean.DynamicSimple;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.LikeData;
import org.flightythought.smile.appserver.bean.PushMessage;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.common.PushCodeEnum;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.JPushUtils;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.flightythought.smile.appserver.database.entity.DynamicDetailsEntity;
import org.flightythought.smile.appserver.database.entity.DynamicDetailsFilesEntity;
import org.flightythought.smile.appserver.database.entity.DynamicEntity;
import org.flightythought.smile.appserver.database.entity.DynamicFilesEntity;
import org.flightythought.smile.appserver.database.entity.FilesEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.entity.UserToDynamicDetailLikeEntity;
import org.flightythought.smile.appserver.database.entity.UserToMessageLikeEntity;
import org.flightythought.smile.appserver.database.repository.DynamicDetailMessageRepository;
import org.flightythought.smile.appserver.database.repository.DynamicDetailsFilesRepository;
import org.flightythought.smile.appserver.database.repository.DynamicDetailsRepository;
import org.flightythought.smile.appserver.database.repository.DynamicFilesRepository;
import org.flightythought.smile.appserver.database.repository.DynamicRepository;
import org.flightythought.smile.appserver.database.repository.FilesRepository;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.database.repository.UserToDynamicDetailLikeRepository;
import org.flightythought.smile.appserver.database.repository.UserToMessageLikeRepository;
import org.flightythought.smile.appserver.dto.AddDynamicDTO;
import org.flightythought.smile.appserver.dto.AddDynamicDetailDTO;
import org.flightythought.smile.appserver.dto.DynamicDetailMessageDTO;
import org.flightythought.smile.appserver.dto.DynamicDetailMessageQueryDTO;
import org.flightythought.smile.appserver.dto.DynamicDetailQueryDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.dto.UserIdQueryDTO;
import org.flightythought.smile.appserver.service.DynamicService;
import org.flightythought.smile.appserver.service.UserService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DynamicRepository dynamicRepository;
    @Autowired
    private DynamicFilesRepository dynamicFilesRepository;
    @Autowired
    private DynamicDetailsRepository dynamicDetailsRepository;
    @Autowired
    private DynamicDetailsFilesRepository dynamicDetailsFilesRepository;
    @Autowired
    private DynamicDetailMessageRepository dynamicDetailMessageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserToDynamicDetailLikeRepository userToDynamicDetailLikeRepository;
    @Autowired
    private UserToMessageLikeRepository userToMessageLikeRepository;
    @Autowired
    private JPushUtils jPushUtils;
    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public DynamicSimple addDynamic(AddDynamicDTO addDynamicDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        DynamicEntity dynamicEntity = new DynamicEntity();
        // 动态标题
        dynamicEntity.setTitle(addDynamicDTO.getTitle());
        // 动态内容
        dynamicEntity.setContent(addDynamicDTO.getContent());
        // 用户ID
        dynamicEntity.setUserId(userId);
        // 动态个数
        dynamicEntity.setDynamicDetailNum(0);
        // 浏览数
        dynamicEntity.setReadNum(0);
        // 是否隐藏
        dynamicEntity.setHidden(addDynamicDTO.getHidden() == null ? false : addDynamicDTO.getHidden());
        // 创建者
        dynamicEntity.setCreateUserName(userId + "");
        // 热门动态
        dynamicEntity.setHot(0);
        // 保存动态
        dynamicEntity = dynamicRepository.save(dynamicEntity);
        // 保存动态图片
        List<DynamicFilesEntity> dynamicFilesEntities = new ArrayList<>();
        List<Integer> fileIds = addDynamicDTO.getFileIds();
        if (fileIds != null && fileIds.size() > 0) {
            DynamicEntity finalDynamicEntity = dynamicEntity;
            fileIds.forEach(fileId -> {
                DynamicFilesEntity dynamicFilesEntity = new DynamicFilesEntity();
                // 动态ID
                dynamicFilesEntity.setDynamicId(finalDynamicEntity.getDynamicId());
                // 动态文件ID
                dynamicFilesEntity.setFileId(fileId);
                dynamicFilesEntities.add(dynamicFilesEntity);
            });
        }
        if (dynamicFilesEntities.size() > 0) {
//            dynamicFilesRepository.saveAll(dynamicFilesEntities);
        }
        // 查询文件
        List<FilesEntity> filesEntities = filesRepository.findByIdIn(fileIds);
        dynamicEntity.setFiles(filesEntities);
        dynamicEntity.setUser(userEntity);
        return getDynamicSimple(dynamicEntity);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public DynamicSimple getDynamicSimple(Integer dynamicId) {
        DynamicEntity dynamicEntity = dynamicRepository.findByDynamicId(dynamicId);
        return getDynamicSimple(dynamicEntity);
    }

    @Override
    public Page<DynamicSimple> getMyDynamic(PageFilterDTO pageFilterDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        Page<DynamicEntity> dynamicEntities = getDynamics(pageFilterDTO, userId, null, true);
        List<DynamicSimple> dynamicSimples;
        if (dynamicEntities != null) {
            long total = dynamicEntities.getTotalElements();
            Pageable pageable = dynamicEntities.getPageable();
            dynamicSimples = dynamicEntities.getContent().stream().map(this::getDynamicSimple).collect(Collectors.toList());
            return new PageImpl<>(dynamicSimples, pageable, total);
        }
        dynamicSimples = new ArrayList<>();
        return new PageImpl<>(dynamicSimples, PageRequest.of(0, 1), 0);
    }

    @Override
    public Page<DynamicSimple> getOtherDynamicWithNoHidden(UserIdQueryDTO userIdQueryDTO) {
        Long userId = userIdQueryDTO.getUserId();
        PageFilterDTO pageFilterDTO = new PageFilterDTO();
        pageFilterDTO.setPageSize(userIdQueryDTO.getPageSize());
        pageFilterDTO.setPageNumber(userIdQueryDTO.getPageNumber());
        Page<DynamicEntity> dynamicEntities = getDynamics(pageFilterDTO, userId, false, userIdQueryDTO.getIncludingMyself());
        List<DynamicSimple> dynamicSimples;
        if (dynamicEntities != null) {
            long total = dynamicEntities.getTotalElements();
            Pageable pageable = dynamicEntities.getPageable();
            dynamicSimples = dynamicEntities.getContent().stream().map(this::getDynamicSimple).collect(Collectors.toList());
            return new PageImpl<>(dynamicSimples, pageable, total);
        }
        dynamicSimples = new ArrayList<>();
        return new PageImpl<>(dynamicSimples, PageRequest.of(0, 1), 0);
    }

    @Override
    public Page<DynamicSimple> getHotDynamic(PageFilterDTO pageFilterDTO) {
        PageRequest pageRequest = PageRequest.of(pageFilterDTO.getPageNumber() - 1, pageFilterDTO.getPageSize());
        Page<DynamicEntity> dynamicEntities = dynamicRepository.findByHotOrderByCreateTimeDesc(1, pageRequest);
        List<DynamicSimple> dynamicSimples = dynamicEntities.stream().map(this::getDynamicSimple).collect(Collectors.toList());
        return new PageImpl<>(dynamicSimples, pageRequest, dynamicEntities.getTotalElements());
    }

    @Override
    @Transactional
    public Page<DynamicEntity> getDynamics(PageFilterDTO pageFilterDTO, Long userId, Boolean hidden, Boolean includingMyself) {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long currentUserId = userEntity.getId();
        if (userId != null && includingMyself != null) {
            if (userId == currentUserId && !includingMyself) {
                includingMyself = true;
            }
        }
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) as total FROM (";
        String sql = "SELECT\n" + "  d.`dynamic_id`\n" + "FROM\n" + "  `tb_dynamic` d\n" + "WHERE 1 = 1 ";
        if (userId != null) {
            sql += " AND d.`user_id` = " + userId;
        }
        if (hidden != null) {
            if (hidden) {
                // 获取隐藏的动态
                sql += " AND d.`hidden` = 1";
            } else {
                sql += " AND d.`hidden` = 0";
            }
        }
        if (includingMyself != null) {
            if (!includingMyself) {
                // 滤除当前登录用户所创建的动态
                sql += " AND d.`user_id` <> " + currentUserId;
            }
        }
        sql += " ORDER BY d.create_time DESC";
        totalSql += sql + ") T";
        // 获取ToTal总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("未查询到动态");
        }
        // 是否存在分页查询
        Integer pageNumber = pageFilterDTO.getPageNumber();
        Integer pageSize = pageFilterDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql += " LIMIT " + (pageNumber - 1) * pageSize + "," + pageSize;
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> dynamicIds = entityManager.createNativeQuery(sql).unwrap(NativeQueryImpl.class).addScalar("dynamic_id", IntegerType.INSTANCE).list();
        List<DynamicEntity> dynamicEntities = dynamicRepository.findByDynamicIdIn(dynamicIds);
        return new PageImpl<>(dynamicEntities, pageable, total);
    }

    @Override
    @Transactional
    public DynamicSimple getDynamicById(Integer dynamicId) {
        // 获取当前登录用户信息
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long currentUserId = userEntity.getId();
        // 获取动态对象
        DynamicEntity dynamicEntity = dynamicRepository.findByDynamicId(dynamicId);
        if (dynamicEntity == null) {
            throw new FlightyThoughtException("没有查询到对应的动态信息");
        }
        long userId = dynamicEntity.getUserId();
        if (userId != currentUserId && dynamicEntity.getHidden()) {
            // 该动态非当前用户创建且处于隐藏状态
            throw new FlightyThoughtException("该动态状态未公开，不能查看");
        }
        if (userId != currentUserId) {
            // 增加浏览数
            dynamicEntity.setReadNum(dynamicEntity.getReadNum() + 1);
            dynamicRepository.save(dynamicEntity);
        }

        // 获取动态对象
        return getDynamicSimple(dynamicEntity);
    }

    @Override
    @Transactional
    public Page<DynamicDetailSimple> getDynamicDetailByDynamicId(DynamicDetailQueryDTO dynamicDetailQueryDTO) {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 根据动态ID获取动态
        Integer dynamicId = dynamicDetailQueryDTO.getDynamicId();
        DynamicEntity dynamicEntity = dynamicRepository.findByDynamicId(dynamicId);
        if (dynamicEntity == null) {
            throw new FlightyThoughtException("dynamicId:" + dynamicId + ",未查询到动态信息");
        }
        long userId = dynamicEntity.getUserId();
        List<DynamicDetailsEntity> dynamicDetailsEntities;
        Pageable pageable;
        long total = 0;
        Integer pageSize = dynamicDetailQueryDTO.getPageSize();
        Integer pageNumber = dynamicDetailQueryDTO.getPageNumber();
        if (userId != userEntity.getId()) {
            if (dynamicEntity.getHidden()) {
                // 其他用户的动态且为隐藏状态
                throw new FlightyThoughtException("该动态信息状态不可见!");
            }
            // 获取动态明细
            if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
                // 获取全部动态信息
                dynamicDetailsEntities = dynamicDetailsRepository.findByDynamicIdAndHiddenOrderByCreateTimeDesc(dynamicId, false);
                pageable = PageRequest.of(0, dynamicDetailsEntities.size() + 1);
                total = dynamicDetailsEntities.size();
            } else {
                pageable = PageRequest.of(pageNumber - 1, pageSize);
                // 分页获取动态信息
                Page<DynamicDetailsEntity> dynamicDetailsEntityPage = dynamicDetailsRepository.findByDynamicIdAndHiddenOrderByCreateTimeDesc(dynamicId, false, pageable);
                dynamicDetailsEntities = dynamicDetailsEntityPage.getContent();
                total = dynamicDetailsEntityPage.getTotalElements();
            }
        } else {
            // 自身所创建的动态
            if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
                // 获取全部动态
                dynamicDetailsEntities = dynamicDetailsRepository.findByDynamicIdOrderByCreateTimeDesc(dynamicId);
                pageable = PageRequest.of(0, dynamicDetailsEntities.size() + 1);
                total = dynamicDetailsEntities.size();
            } else {
                pageable = PageRequest.of(pageNumber - 1, pageSize);
                // 分页获取动态信息
                Page<DynamicDetailsEntity> dynamicDetailsEntityPage = dynamicDetailsRepository.findByDynamicIdOrderByCreateTimeDesc(dynamicId, pageable);
                dynamicDetailsEntities = dynamicDetailsEntityPage.getContent();
                total = dynamicDetailsEntityPage.getTotalElements();
            }
        }
        // 获取动态明细对象集合
        List<DynamicDetailSimple> dynamicDetailSimples = getDynamicDetailSimples(dynamicDetailsEntities);
        return new PageImpl<>(dynamicDetailSimples, pageable, total);
    }

    @Override
    public List<DynamicDetailSimple> getDynamicDetailSimples(List<DynamicDetailsEntity> dynamicDetailsEntities) {
        if (dynamicDetailsEntities.size() > 0) {
            // 获取当前登录用户
            UserEntity userEntity = platformUtils.getCurrentLoginUser();
            String domainPort = platformUtils.getDomainPort();
            // 获取所有动态明细ID
            List<Integer> dynamicDetailIds = dynamicDetailsEntities.stream().map(DynamicDetailsEntity::getDynamicDetailId).collect(Collectors.toList());
            // 判断当前用户是否点赞
            List<UserToDynamicDetailLikeEntity> likeEntities = userToDynamicDetailLikeRepository.findByUserIdAndDynamicDetailIdIn(userEntity.getId(), dynamicDetailIds);
            Map<Integer, UserToDynamicDetailLikeEntity> likeEntityMap = likeEntities.stream()
                    .collect(Collectors.toMap(UserToDynamicDetailLikeEntity::getDynamicDetailId, Function.identity()));
            List<DynamicDetailSimple> dynamicDetailSimples = dynamicDetailsEntities.stream().map(dynamicDetailsEntity -> {
                DynamicDetailSimple dynamicDetailSimple = getDynamicDetailSimple(dynamicDetailsEntity);
                // 动态明细ID
                Integer detailDetailId = dynamicDetailsEntity.getDynamicDetailId();
                if (likeEntityMap.get(detailDetailId) != null) {
                    dynamicDetailSimple.setLike(true);
                } else {
                    dynamicDetailSimple.setLike(false);
                }
                // 动态图片
                List<FilesEntity> filesEntities = dynamicDetailsEntity.getFiles();
                List<FileInfo> fileInfos = filesEntities.stream().map(filesEntity -> platformUtils.getFileInfo(filesEntity, domainPort)).collect(Collectors.toList());
                dynamicDetailSimple.setFiles(fileInfos);
                return dynamicDetailSimple;
            }).collect(Collectors.toList());
            return dynamicDetailSimples;
        }
        return new ArrayList<>();
    }

    @Override
    public DynamicSimple getDynamicSimple(DynamicEntity dynamicEntity) {
        if (dynamicEntity != null) {
            DynamicSimple dynamicSimple = new DynamicSimple();
            // 动态ID
            dynamicSimple.setDynamicId(dynamicEntity.getDynamicId());
            // 动态标题
            dynamicSimple.setTitle(dynamicEntity.getTitle());
            // 动态内容
            dynamicSimple.setContent(dynamicEntity.getContent());
            // 用户
            UserEntity user = dynamicEntity.getUser();
            UserInfo userInfo = userService.getUserInfo(user);
            dynamicSimple.setUser(userInfo);
            // 动态个数
            dynamicSimple.setDynamicDetailNum(dynamicEntity.getDynamicDetailNum());
            // 浏览数
            dynamicSimple.setReadNum(dynamicEntity.getReadNum());
            // 是否隐藏
            dynamicSimple.setHidden(dynamicEntity.getHidden());
            // 文件
            List<FilesEntity> filesEntities = dynamicEntity.getFiles();
            List<FileInfo> fileInfos = new ArrayList<>();
            if (filesEntities != null && filesEntities.size() > 0) {
                String domainPort = platformUtils.getDomainPort();
                filesEntities.forEach(filesEntity -> fileInfos.add(platformUtils.getFileInfo(filesEntity, domainPort)));
            }
            dynamicSimple.setFiles(fileInfos);
            // 创建时间
            dynamicSimple.setCreateTime(dynamicEntity.getCreateTime());
            return dynamicSimple;
        }
        return null;
    }

    @Override
    @Transactional
    public DynamicDetailSimple addDynamicDetail(AddDynamicDetailDTO addDynamicDetailDTO) {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        DynamicDetailsEntity dynamicDetailsEntity = new DynamicDetailsEntity();
        // 动态ID
        dynamicDetailsEntity.setDynamicId(addDynamicDetailDTO.getDynamicId());
        // 获取动态
        DynamicEntity dynamicEntity = dynamicRepository.findByDynamicId(addDynamicDetailDTO.getDynamicId());
        // 新增动态数+1
        dynamicEntity.setDynamicDetailNum(dynamicEntity.getDynamicDetailNum() + 1);
        dynamicRepository.save(dynamicEntity);
        // 动态内容
        dynamicDetailsEntity.setContent(addDynamicDetailDTO.getContent());
        // 用户ID
        dynamicDetailsEntity.setUserId(userId);
        // 转发个数
        dynamicDetailsEntity.setForwardNum(0);
        // 评论个数
        dynamicDetailsEntity.setMessageNum(0);
        // 点赞个数
        dynamicDetailsEntity.setLikeNum(0);
        // 浏览数
        dynamicDetailsEntity.setReadNum(0);
        // 是否隐藏
        dynamicDetailsEntity.setHidden(addDynamicDetailDTO.getHidden() == null ? false : addDynamicDetailDTO.getHidden());
        // 创建者
        dynamicDetailsEntity.setCreateUserName(userId + "");
        // 保存动态明细
        dynamicDetailsEntity = dynamicDetailsRepository.save(dynamicDetailsEntity);
        // 保存文件信息
        List<DynamicDetailsFilesEntity> dynamicDetailsFilesEntities = new ArrayList<>();
        List<Integer> fileIds = addDynamicDetailDTO.getFileIds();
        if (fileIds != null && fileIds.size() > 0) {
            Integer dynamicDetailId = dynamicDetailsEntity.getDynamicDetailId();
            fileIds.forEach(fileId -> {
                DynamicDetailsFilesEntity dynamicDetailsFilesEntity = new DynamicDetailsFilesEntity();
                // 发布动态明细ID
                dynamicDetailsFilesEntity.setDynamicDetailId(dynamicDetailId);
                // 文件ID
                dynamicDetailsFilesEntity.setFileId(fileId);
                dynamicDetailsFilesEntities.add(dynamicDetailsFilesEntity);
            });
            // 保存文件信息
//            dynamicDetailsFilesRepository.saveAll(dynamicDetailsFilesEntities);
        }
        List<FilesEntity> filesEntities = filesRepository.findByIdIn(fileIds);
        dynamicDetailsEntity.setFiles(filesEntities);
        return getDynamicDetailSimple(dynamicDetailsEntity);
    }

    @Transactional
    @Override
    public DynamicDetailSimple getDynamicDetailSimple(Integer dynamicDetailId) {
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        return getDynamicDetailSimple(dynamicDetailsEntity);
    }

    @Override
    public DynamicDetailSimple getDynamicDetailSimple(DynamicDetailsEntity dynamicDetailsEntity) {
        if (dynamicDetailsEntity != null) {
            String domainPort = platformUtils.getDomainPort();
            DynamicDetailSimple dynamicDetailSimple = new DynamicDetailSimple();
            // 动态明细ID
            dynamicDetailSimple.setDynamicDetailId(dynamicDetailsEntity.getDynamicDetailId());
            // 动态ID
            dynamicDetailSimple.setDynamicId(dynamicDetailsEntity.getDynamicId());
            // 动态内容
            dynamicDetailSimple.setContent(dynamicDetailsEntity.getContent());
            // 用户
            UserEntity userEntity = dynamicDetailsEntity.getUser();
            if (userEntity != null) {
                dynamicDetailSimple.setUser(userService.getUserInfo(userEntity));
            }
            // 转发个数
            dynamicDetailSimple.setForwardNum(dynamicDetailsEntity.getForwardNum());
            // 评论个数
            dynamicDetailSimple.setMessageNum(dynamicDetailsEntity.getMessageNum());
            // 点赞个数
            dynamicDetailSimple.setLikeNum(dynamicDetailsEntity.getLikeNum());
            // 浏览个数
            dynamicDetailSimple.setReadNum(dynamicDetailsEntity.getReadNum());
            // 是否公开
            dynamicDetailSimple.setHidden(dynamicDetailsEntity.getHidden());
            // 创建时间
            dynamicDetailSimple.setCreateTime(dynamicDetailsEntity.getCreateTime());
            // 文件
            List<FilesEntity> filesEntities = dynamicDetailsEntity.getFiles();
            if (filesEntities != null) {
               List<FileInfo> fileInfo =  filesEntities.stream().map(file->platformUtils.getFileInfo(file, domainPort)).collect(Collectors.toList());
               dynamicDetailSimple.setFiles(fileInfo);
            }
            return dynamicDetailSimple;
        }
        return null;
    }

    @Override
    public DynamicDetailMessageEntity addDynamicDetailMessage(DynamicDetailMessageDTO dynamicDetailMessageDTO) {
        // 获取当前登录用户
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailMessageDTO.getDynamicDetailId());
        if (dynamicDetailsEntity == null) {
            throw new FlightyThoughtException("没有找到评论的动态明细");
        }
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        DynamicDetailMessageEntity dynamicDetailMessageEntity = new DynamicDetailMessageEntity();
        // 动态明细ID
        dynamicDetailMessageEntity.setDynamicDetailId(dynamicDetailMessageDTO.getDynamicDetailId());
        // 评论内容
        dynamicDetailMessageEntity.setMessage(dynamicDetailMessageDTO.getMessage());
        // 父级ID
        dynamicDetailMessageEntity.setParentId(dynamicDetailMessageDTO.getParentId());
        // 创建者用户ID
        dynamicDetailMessageEntity.setFromUserId(dynamicDetailMessageDTO.getFromUserId());
        // 发送给用户ID
        dynamicDetailMessageEntity.setToUserId(dynamicDetailMessageDTO.getToUserId());
        // 点赞个数
        dynamicDetailMessageEntity.setLikeNum(0);
        // 接受者用户是否查看
        dynamicDetailMessageEntity.setRead(false);
        // 标志类型
        dynamicDetailMessageEntity.setFlagType(dynamicDetailMessageDTO.getFlagType());
        // 创建者
        dynamicDetailMessageEntity.setCreateUserName(userEntity.getId() + "");
        // 保存
        dynamicDetailMessageEntity = dynamicDetailMessageRepository.save(dynamicDetailMessageEntity);
        // 评论数+1
        dynamicDetailsEntity.setMessageNum(dynamicDetailsEntity.getMessageNum() == null ? 1 : dynamicDetailsEntity.getMessageNum() + 1);
        dynamicDetailsRepository.save(dynamicDetailsEntity);
        // 自己评论自己不推送
        //        if (!userEntity.getId().equals(dynamicDetailMessageDTO.getToUserId()) && !dynamicDetailMessageDTO.getFromUserId().equals(dynamicDetailMessageDTO.getToUserId())) {
        // 评论信息推送
        PushMessage<DynamicDetailMessageSimple> pushMessage = new PushMessage<>();
        // 封装评论用户
        DynamicDetailMessageEntity detailMessageEntity = new DynamicDetailMessageEntity();
        BeanUtils.copyProperties(dynamicDetailMessageEntity, detailMessageEntity);
        UserEntity fromUser = userRepository.getOne(detailMessageEntity.getFromUserId());
        UserEntity toUser = userRepository.getOne(detailMessageEntity.getToUserId());
        detailMessageEntity.setFromUser(fromUser);
        detailMessageEntity.setToUser(toUser);
        List<DynamicDetailMessageEntity> dynamicDetailMessageEntities = new ArrayList<>();
        dynamicDetailMessageEntities.add(detailMessageEntity);
        List<DynamicDetailMessageSimple> dynamicDetailMessageSimples = new ArrayList<>();
        getDynamicDetailMessageSimple(dynamicDetailMessageEntities, dynamicDetailMessageSimples);
        DynamicDetailMessageSimple dynamicDetailMessageSimple = dynamicDetailMessageSimples.get(0);
        pushMessage.setData(dynamicDetailMessageSimple);
        pushMessage.setCode(PushCodeEnum.DYNAMIC_MESSAGE.getMessage());
        pushMessage.setTitle("您有一条新评论");
        pushMessage.setMessage("您有一条新评论");
        pushMessage.setType(Constants.NOTICE_MESSAGE);
        jPushUtils.pushData(pushMessage, dynamicDetailMessageDTO.getToUserId());
        //        }
        return dynamicDetailMessageEntity;
    }

    @Override
    @Transactional
    public List<DynamicDetailMessageSimple> getDynamicDetailMessage(Integer dynamicDetailId) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        // 获取动态明细
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        if (dynamicDetailsEntity != null && dynamicDetailsEntity.getUserId() != userId) {
            if (dynamicDetailsEntity.getHidden()) {
                throw new FlightyThoughtException("访问者无权限");
            }
        }
        List<DynamicDetailMessageEntity> dynamicDetailMessageEntities = dynamicDetailMessageRepository.findByDynamicDetailIdAndParentIdIsNullOrderByCreateTimeDesc(dynamicDetailId);
        List<DynamicDetailMessageSimple> result = new ArrayList<>();
        getDynamicDetailMessageSimple(dynamicDetailMessageEntities, result);
        return result;
    }

    @Override
    @Transactional
    public Page<DynamicDetailMessageSimple> getDynamicDetailMessage(DynamicDetailMessageQueryDTO dynamicDetailMessageQueryDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        Integer dynamicDetailId = dynamicDetailMessageQueryDTO.getDynamicDetailId();
        // 获取动态明细
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        if (dynamicDetailsEntity != null && dynamicDetailsEntity.getUserId() != userId) {
            if (dynamicDetailsEntity.getHidden()) {
                throw new FlightyThoughtException("访问者无权限");
            }
        }
        Pageable pageable = PageRequest.of(dynamicDetailMessageQueryDTO.getPageNumber() - 1, dynamicDetailMessageQueryDTO.getPageSize());
        Page<DynamicDetailMessageEntity> dynamicDetailMessageEntities = dynamicDetailMessageRepository.findByDynamicDetailIdAndParentIdIsNullOrderByCreateTimeDesc(dynamicDetailId,
                pageable);
        List<DynamicDetailMessageSimple> result = new ArrayList<>();
        getDynamicDetailMessageSimple(dynamicDetailMessageEntities.getContent(), result);
        return new PageImpl<>(result, dynamicDetailMessageEntities.getPageable(), dynamicDetailMessageEntities.getTotalElements());
    }

    @Override
    @Transactional
    public Page<DynamicDetailMessageSimple> getDynamicDetailMessageInfo(DynamicDetailMessageQueryDTO dynamicDetailMessageQueryDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        Integer dynamicDetailId = dynamicDetailMessageQueryDTO.getDynamicDetailId();
        // 获取动态明细
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        if (dynamicDetailsEntity != null && dynamicDetailsEntity.getUserId() != userId) {
            if (dynamicDetailsEntity.getHidden()) {
                throw new FlightyThoughtException("访问者无权限");
            }
        }
        Pageable pageable = PageRequest.of(dynamicDetailMessageQueryDTO.getPageNumber() - 1, dynamicDetailMessageQueryDTO.getPageSize());
        Integer messageId = dynamicDetailMessageQueryDTO.getMessageId();
        Page<DynamicDetailMessageEntity> dynamicDetailMessageEntities = dynamicDetailMessageRepository.findByDynamicDetailIdAndParentIdOrderByCreateTimeDesc(dynamicDetailId,
                messageId, pageable);
        List<DynamicDetailMessageSimple> result = new ArrayList<>();
        getDynamicDetailMessageSimple(dynamicDetailMessageEntities.getContent(), result);
        return new PageImpl<>(result, dynamicDetailMessageEntities.getPageable(), dynamicDetailMessageEntities.getTotalElements());
    }

    @Override
    public void getDynamicDetailMessageSimple(List<DynamicDetailMessageEntity> dynamicDetailMessageEntities, List<DynamicDetailMessageSimple> result) {
        if (dynamicDetailMessageEntities == null || dynamicDetailMessageEntities.size() == 0) {
            return;
        }
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        List<Integer> messageIds = dynamicDetailMessageEntities.stream().map(DynamicDetailMessageEntity::getId).collect(Collectors.toList());
        // 获取用户点赞的信息集合
        List<UserToMessageLikeEntity> userToMessageLikeEntities = userToMessageLikeRepository.findByUserIdAndMessageIdIn(userId, messageIds);
        Map<Integer, UserToMessageLikeEntity> likeEntityMap = userToMessageLikeEntities.stream()
                .collect(Collectors.toMap(UserToMessageLikeEntity::getMessageId, Function.identity()));
        dynamicDetailMessageEntities.forEach(dynamicDetailMessageEntity -> {
            DynamicDetailMessageSimple dynamicDetailMessageSimple = new DynamicDetailMessageSimple();
            result.add(dynamicDetailMessageSimple);
            // 主键ID
            dynamicDetailMessageSimple.setId(dynamicDetailMessageEntity.getId());
            // 动态明细ID
            dynamicDetailMessageSimple.setDynamicDetailId(dynamicDetailMessageEntity.getDynamicDetailId());
            // 评论内容
            dynamicDetailMessageSimple.setMessage(dynamicDetailMessageEntity.getMessage());
            // 父级ID
            dynamicDetailMessageSimple.setParentId(dynamicDetailMessageEntity.getParentId());
            // 发送者
            UserEntity fromUserEntity = dynamicDetailMessageEntity.getFromUser();
            if (fromUserEntity != null) {
                UserInfo fromUser = userService.getUserInfo(fromUserEntity);
                dynamicDetailMessageSimple.setFromUser(fromUser);
            }
            // 接收者
            UserEntity toUserEntity = dynamicDetailMessageEntity.getToUser();
            if (toUserEntity != null) {
                UserInfo toUser = userService.getUserInfo(toUserEntity);
                dynamicDetailMessageSimple.setToUser(toUser);
            }
            // 接收者是否阅读
            dynamicDetailMessageSimple.setRead(dynamicDetailMessageEntity.getRead());
            // 点赞个数
            dynamicDetailMessageSimple.setLikeNum(dynamicDetailMessageEntity.getLikeNum());
            // TODO 当前用户是否点赞
            if (likeEntityMap.get(dynamicDetailMessageEntity.getId()) != null) {
                dynamicDetailMessageSimple.setLike(true);
            } else {
                dynamicDetailMessageSimple.setLike(false);
            }
            // flagType
            dynamicDetailMessageSimple.setFlagType(dynamicDetailMessageEntity.getFlagType());
            // 创建时间
            dynamicDetailMessageSimple.setCreateTime(dynamicDetailMessageEntity.getCreateTime());
            // 判断是否有子集
            List<DynamicDetailMessageEntity> childDetailMessageEntities = dynamicDetailMessageEntity.getChildMessage();
            if (childDetailMessageEntities != null && childDetailMessageEntities.size() > 0) {
                List<DynamicDetailMessageSimple> child = new ArrayList<>();
                dynamicDetailMessageSimple.setChildMessage(child);
                getDynamicDetailMessageSimple(childDetailMessageEntities, child);
            }
        });
    }

    @Override
    public void likeDynamicDetail(Integer dynamicDetailId) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        // 获取动态明细
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        if (dynamicDetailsEntity == null) {
            throw new FlightyThoughtException("没有找到对应的动态明细");
        }
        // 查询判断当前用户有没有点赞
        UserToDynamicDetailLikeEntity likeEntity = userToDynamicDetailLikeRepository.findByUserIdAndDynamicDetailId(userId, dynamicDetailId);
        if (likeEntity != null) {
            // 取消点赞
            dynamicDetailsEntity.setLikeNum(dynamicDetailsEntity.getLikeNum() - 1);
            userToDynamicDetailLikeRepository.delete(likeEntity);
        } else {
            // 点赞
            // 点赞数 + 1
            dynamicDetailsEntity.setLikeNum(dynamicDetailsEntity.getLikeNum() == null ? 1 : dynamicDetailsEntity.getLikeNum() + 1);
            likeEntity = new UserToDynamicDetailLikeEntity();
            likeEntity.setUserId(userId);
            likeEntity.setDynamicDetailId(dynamicDetailId);
            userToDynamicDetailLikeRepository.save(likeEntity);
            // 点赞自己发布的不推送
            //            if (userId != dynamicDetailsEntity.getUserId()) {
            // 评论点赞推送
            // 点赞用户
            UserInfo userInfo = userService.getUserInfo(userId);
            DynamicDetailSimple dynamicDetailSimple = getDynamicDetailSimple(dynamicDetailsEntity);
            LikeData<DynamicDetailSimple> likeData = new LikeData<>();
            likeData.setData(dynamicDetailSimple);
            likeData.setLikeUser(userInfo);
            PushMessage<LikeData> pushMessage = new PushMessage<>();
            pushMessage.setData(likeData);
            pushMessage.setType(Constants.NOTICE_LIKE);
            pushMessage.setCode(PushCodeEnum.DYNAMIC_LIKE.getMessage());
            pushMessage.setTitle("您发表的动态有用户点赞");
            pushMessage.setMessage("您发表的动态有用户点赞");
            jPushUtils.pushData(pushMessage, dynamicDetailsEntity.getUserId());
            //            }
        }
        dynamicDetailsRepository.save(dynamicDetailsEntity);
    }

    @Override
    public void likeDynamicDetailMessage(Integer messageId) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        long userId = userEntity.getId();
        // 获取动态明细评论
        DynamicDetailMessageEntity dynamicDetailMessageEntity = dynamicDetailMessageRepository.findById(messageId);
        if (dynamicDetailMessageEntity == null) {
            throw new FlightyThoughtException("没有找到对应的评论信息");
        }
        // 查询判断当前用户有没有点赞
        UserToMessageLikeEntity likeEntity = userToMessageLikeRepository.findByUserIdAndMessageId(userId, messageId);
        if (likeEntity != null) {
            // 取消点赞
            dynamicDetailMessageEntity.setLikeNum(dynamicDetailMessageEntity.getLikeNum() - 1);
            userToMessageLikeRepository.delete(likeEntity);
        } else {
            // 点赞
            dynamicDetailMessageEntity.setLikeNum(dynamicDetailMessageEntity.getLikeNum() == null ? 1 : dynamicDetailMessageEntity.getLikeNum() + 1);
            likeEntity = new UserToMessageLikeEntity();
            likeEntity.setUserId(userId);
            likeEntity.setMessageId(messageId);
            userToMessageLikeRepository.save(likeEntity);
            // 点赞自己发布的不推送
            //            if (userId != dynamicDetailMessageEntity.getFromUserId()) {
            // 动态明细评论信息推送
            // 获取点赞用户
            UserInfo userInfo = userService.getUserInfo(userId);
            List<DynamicDetailMessageEntity> dynamicDetailMessageEntities = new ArrayList<>();
            List<DynamicDetailMessageSimple> dynamicDetailMessageSimples = new ArrayList<>();
            dynamicDetailMessageEntities.add(dynamicDetailMessageEntity);
            getDynamicDetailMessageSimple(dynamicDetailMessageEntities, dynamicDetailMessageSimples);
            DynamicDetailMessageSimple dynamicDetailMessageSimple = dynamicDetailMessageSimples.get(0);
            LikeData<DynamicDetailMessageSimple> likeData = new LikeData<>();
            likeData.setLikeUser(userInfo);
            likeData.setData(dynamicDetailMessageSimple);
            PushMessage<LikeData> pushMessage = new PushMessage<>();
            pushMessage.setMessage("有人点赞您发表的评论");
            pushMessage.setTitle("有人点赞您发表的评论");
            pushMessage.setType(Constants.NOTICE_LIKE);
            pushMessage.setCode(PushCodeEnum.DYNAMIC_MESSAGE_LIKE.getMessage());
            pushMessage.setData(likeData);
            jPushUtils.pushData(pushMessage, dynamicDetailMessageEntity.getFromUserId());
            //            }
        }
        dynamicDetailMessageRepository.save(dynamicDetailMessageEntity);
    }

    @Override
    @Transactional
    public DynamicDetailSimple getDynamicDetail(Integer dynamicDetailId) {
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        return getDynamicDetailSimple(dynamicDetailsEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDynamic(Integer dynamicId) {
        DynamicEntity dynamicEntity = dynamicRepository.findByDynamicId(dynamicId);
        if (dynamicEntity != null) {
            dynamicRepository.delete(dynamicEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDynamicDetail(Integer dynamicDetailId) {
        DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
        if (dynamicDetailsEntity != null) {
            // 明细删除减小明细统计个数
            DynamicEntity dynamicEntity = dynamicRepository.findByDynamicId(dynamicDetailsEntity.getDynamicId());
            dynamicEntity.setReadNum(dynamicEntity.getReadNum() - 1);
            dynamicRepository.save(dynamicEntity);
            dynamicDetailsRepository.delete(dynamicDetailsEntity);
        }
    }

    @Override
    public void deleteDynamicDetailMessage(Integer messageId) {
        DynamicDetailMessageEntity dynamicDetailMessageEntity = dynamicDetailMessageRepository.findById(messageId);
        if (dynamicDetailMessageEntity != null) {
            Integer dynamicDetailId = dynamicDetailMessageEntity.getDynamicDetailId();
            dynamicDetailMessageRepository.delete(dynamicDetailMessageEntity);
            // 更新message个数
            DynamicDetailsEntity dynamicDetailsEntity = dynamicDetailsRepository.findByDynamicDetailId(dynamicDetailId);
            List<DynamicDetailMessageEntity> list = dynamicDetailMessageRepository.findByDynamicDetailId(dynamicDetailId);
            int count = 0;
            if (list != null) {
                count = list.size();
            }
            dynamicDetailsEntity.setMessageNum(count);
            dynamicDetailsRepository.save(dynamicDetailsEntity);
        }
    }
}
