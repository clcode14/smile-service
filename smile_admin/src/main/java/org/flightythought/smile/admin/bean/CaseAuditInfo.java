package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

@Data
public class CaseAuditInfo {

    /**
     * 养生旅程ID
     */
    private Integer journeyId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 旅程名称
     */
    private String journeyName;

    /**
     * 养生成果
     */
    private List<String> healthResult;

    /**
     * 疾病
     */
    private List<String> diseaseNames;

    /**
     * 课程
     */
    private List<String> courses;

    /**
     * 养生
     */
    private List<String> healths;

    /**
     * 解决方案
     */
    private List<String> solutions;
}
