package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DynamicSimple {

    /**
     * 动态ID
     */
    private Integer dynamicId;

    /**
     * 动态标题
     */
    private String title;

    /**
     * 动态内容
     */
    private String content;

    /**
     * 用户
     */
    private UserInfo user;

    /**
     * 动态个数
     */
    private Integer dynamicDetailNum;

    /**
     * 浏览数
     */
    private Integer readNum;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 文件集合
     */
    private List<FileInfo> files;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;
}
