package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName NoticeNumber
 * @CreateTime 2019/5/28 20:32
 * @Description: TODO
 */
@Data
public class NoticeNumber {
    /**
     * 系统通知
     */
    private int systemNotice;

    /**
     * 点赞通知
     */
    private int likeNotice;

    /**
     * 粉丝通知
     */
    private int fansNotice;

    /**
     * 评论通知
     */
    private int messageNotice;
}
