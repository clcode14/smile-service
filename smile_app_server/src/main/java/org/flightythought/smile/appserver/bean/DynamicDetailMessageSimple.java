package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DynamicDetailMessageSimple
 * @CreateTime 2019/4/24 0:47
 * @Description: TODO
 */
@Data
public class DynamicDetailMessageSimple {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 动态明细ID
     */
    private Integer dynamicDetailId;

    /**
     * 评论内容
     */
    private String message;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 发送者
     */
    private UserInfo fromUser;

    /**
     * 接受者
     */
    private UserInfo toUser;

    /**
     * 接受者是否阅读
     */
    private Boolean read;

    /**
     * 下级信息
     */
    private List<DynamicDetailMessageSimple> childMessage;

    /**
     * 点赞个数
     */
    private Integer likeNum;

    /**
     * 当前用户是否点赞
     */
    private Boolean like;
}
