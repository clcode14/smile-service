package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.DynamicDetailMessageSimple;
import org.flightythought.smile.appserver.bean.DynamicDetailSimple;
import org.flightythought.smile.appserver.bean.DynamicSimple;
import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.flightythought.smile.appserver.database.entity.DynamicDetailsEntity;
import org.flightythought.smile.appserver.database.entity.DynamicEntity;
import org.flightythought.smile.appserver.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright 2019 Flighty-thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DynamicService.java
 * @CreateTime 2019/5/1 12:12
 * @Description: 动态Service
 */
public interface DynamicService {
    /**
     * 新增动态
     *
     * @param addDynamicDTO 添加动态DTO
     * @return 动态简单对象
     */
    DynamicSimple addDynamic(AddDynamicDTO addDynamicDTO);

    DynamicSimple getDynamicSimple(Integer dynamicId);

    DynamicDetailSimple getDynamicDetailSimple(Integer dynamicDetailId);

    /**
     * 获取我的动态
     *
     * @param pageFilterDTO 分页DTO
     * @return 分页动态简单对象
     */
    Page<DynamicSimple> getMyDynamic(PageFilterDTO pageFilterDTO);

    /**
     * 获取其他人公开动态
     *
     * @param userIdQueryDTO 用户查询DTO
     * @return 分页动态简单对象
     */
    Page<DynamicSimple> getOtherDynamicWithNoHidden(UserIdQueryDTO userIdQueryDTO);

    Page<DynamicSimple> getHotDynamic(PageFilterDTO pageFilterDTO);

    /**
     * 获取分页动态实体集合
     *
     * @param pageFilterDTO   分页查询DTO
     * @param userId          用户ID
     * @param hidden          是否查询公开动态（为null查询全部）
     * @param includingMyself 是否包含自身创建动态（用户ID为自身，该参数必须为true方可查询到结果）
     * @return 分页动态实体集合
     */
    Page<DynamicEntity> getDynamics(PageFilterDTO pageFilterDTO, Long userId, Boolean hidden, Boolean includingMyself);

    /**
     * 根据动态ID获取动态详情
     *
     * @param dynamicId 动态ID
     * @return 动态对象
     */
    DynamicSimple getDynamicById(Integer dynamicId);

    /**
     * 获取动态明细分页
     *
     * @param dynamicDetailQueryDTO 动态明细查询DTO
     * @return 动态明细分页
     */
    Page<DynamicDetailSimple> getDynamicDetailByDynamicId(DynamicDetailQueryDTO dynamicDetailQueryDTO);

    /**
     * 获取动态明细对象集合
     *
     * @param dynamicDetailsEntities 动态明细实体集合
     * @return 动态明细对象集合
     */
    List<DynamicDetailSimple> getDynamicDetailSimples(List<DynamicDetailsEntity> dynamicDetailsEntities);

    /**
     * 获取动态简单对象
     *
     * @param dynamicEntity 动态实体对象
     * @return 动态简单对象
     */
    DynamicSimple getDynamicSimple(DynamicEntity dynamicEntity);

    /**
     * 添加动态明细
     *
     * @param addDynamicDetailDTO 新增动态明细DTO
     * @return 动态明细简单对象
     */
    DynamicDetailSimple addDynamicDetail(AddDynamicDetailDTO addDynamicDetailDTO);

    /**
     * 获取动态明细简单对象（返回结果不包含自身点赞判断）
     *
     * @param dynamicDetailsEntity 动态明细实体类
     * @return 动态明细简单对象
     */
    DynamicDetailSimple getDynamicDetailSimple(DynamicDetailsEntity dynamicDetailsEntity);

    /**
     * 评论动态明细
     *
     * @param dynamicDetailMessageDTO 动态明细评论信息DTO
     * @return 动态明细评论实体
     */
    DynamicDetailMessageEntity addDynamicDetailMessage(DynamicDetailMessageDTO dynamicDetailMessageDTO);

    /**
     * 根据动态明细ID获取动态评论信息
     *
     * @param dynamicDetailId 动态明细ID
     * @return 动态明细评论简单对象集合
     */
    List<DynamicDetailMessageSimple> getDynamicDetailMessage(Integer dynamicDetailId);

    Page<DynamicDetailMessageSimple> getDynamicDetailMessage(DynamicDetailMessageQueryDTO dynamicDetailMessageQueryDTO);

    Page<DynamicDetailMessageSimple> getDynamicDetailMessageInfo(DynamicDetailMessageQueryDTO dynamicDetailMessageQueryDTO);

    /**
     * 递归获取动态明细评论集合
     *
     * @param dynamicDetailMessageEntities 动态明细评论集合
     * @param result                       存放结果的集合
     */
    void getDynamicDetailMessageSimple(List<DynamicDetailMessageEntity> dynamicDetailMessageEntities, List<DynamicDetailMessageSimple> result);

    /**
     * 用户点赞或取消点赞动态明细
     *
     * @param dynamicDetailId 动态明细ID
     */
    void likeDynamicDetail(Integer dynamicDetailId);

    /**
     * 用户点赞或取消点赞动态明细评论
     *
     * @param messageId 评论ID
     */
    void likeDynamicDetailMessage(Integer messageId);
    
    /**
     * 根据动态明明细ID获取动态明细
     * 
     * @param dynamicDetailId
     * @return
     */
    DynamicDetailSimple getDynamicDetail(Integer dynamicDetailId);
}
