package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.*;
import org.flightythought.smile.appserver.dto.AddDynamicDTO;
import org.flightythought.smile.appserver.dto.AddDynamicDetailDTO;
import org.flightythought.smile.appserver.dto.DynamicDetailMessageDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.DynamicService;
import org.flightythought.smile.appserver.service.UserService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        // 是否公开
        dynamicEntity.setHidden(addDynamicDTO.getHidden());
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
            dynamicFilesRepository.saveAll(dynamicFilesEntities);
        }
        dynamicEntity.setUser(userEntity);
        return getDynamicSimple(dynamicEntity);
    }

    @Override
    public Page<DynamicSimple> getMyDynamic(PageFilterDTO pageFilterDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        Page<DynamicEntity> dynamicEntities = getDynamics(pageFilterDTO, userId, null);
        if (dynamicEntities != null) {
            long total = dynamicEntities.getTotalElements();
            Pageable pageable = dynamicEntities.getPageable();
            List<DynamicSimple> dynamicSimples = dynamicEntities.getContent().stream().map(this::getDynamicSimple).collect(Collectors.toList());
            return new PageImpl<>(dynamicSimples, pageable, total);
        }
        return null;
    }

    @Override
    @Transactional
    public Page<DynamicEntity> getDynamics(PageFilterDTO pageFilterDTO, Long userId, Boolean hidden) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) as total FROM (";
        String sql = "SELECT\n" +
                "  d.`dynamic_id`\n" +
                "FROM\n" +
                "  `tb_dynamic` d\n" +
                "WHERE 1 = 1 ";
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
        List<Integer> dynamicIds = entityManager.createNativeQuery(sql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("dynamic_id", IntegerType.INSTANCE)
                .list();
        List<DynamicEntity> dynamicEntities = dynamicRepository.findByDynamicIdIn(dynamicIds);
        return new PageImpl<>(dynamicEntities, pageable, total);
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
        // 是否公开
        dynamicDetailsEntity.setHidden(addDynamicDetailDTO.getHidden());
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
            dynamicDetailsFilesRepository.saveAll(dynamicDetailsFilesEntities);
        }
        return getDynamicDetailSimple(dynamicDetailsEntity);
    }

    @Override
    public DynamicDetailSimple getDynamicDetailSimple(DynamicDetailsEntity dynamicDetailsEntity) {
        if (dynamicDetailsEntity != null) {
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
            return dynamicDetailSimple;
        }
        return null;
    }

    @Override
    public DynamicDetailMessageEntity addDynamicDetailMessage(DynamicDetailMessageDTO dynamicDetailMessageDTO) {
        // 获取当前登录用户
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
        // 创建者
        dynamicDetailMessageEntity.setCreateUserName(userEntity.getId() + "");
        // 保存
        return dynamicDetailMessageRepository.save(dynamicDetailMessageEntity);
    }

    @Override
    @Transactional
    public List<DynamicDetailMessageSimple> getDynamicDetailMessage(Integer dynamicDetailId) {
        List<DynamicDetailMessageEntity> dynamicDetailMessageEntities = dynamicDetailMessageRepository.findByDynamicDetailIdAndParentIdIsNull(dynamicDetailId);
        List<DynamicDetailMessageSimple> result = new ArrayList<>();
        getDynamicDetailMessageSimple(dynamicDetailMessageEntities, result);
        return result;
    }

    public void getDynamicDetailMessageSimple(List<DynamicDetailMessageEntity> dynamicDetailMessageEntities, List<DynamicDetailMessageSimple> result) {
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
            // 判断是否有子集
            List<DynamicDetailMessageEntity> childDetailMessageEntities = dynamicDetailMessageEntity.getChildMessage();
            if (childDetailMessageEntities != null && childDetailMessageEntities.size() > 0) {
                List<DynamicDetailMessageSimple> child = new ArrayList<>();
                dynamicDetailMessageSimple.setChildMessage(child);
                getDynamicDetailMessageSimple(childDetailMessageEntities, child);
            }
        });
    }
}
