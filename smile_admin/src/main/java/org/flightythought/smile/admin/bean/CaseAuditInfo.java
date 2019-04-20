package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

@Data
public class CaseAuditInfo {

    private Integer journeyId;

    private String userName;

    private Long userId;

    private String journeyName;

    private List<String> diseaseNames;

    private List<String> joinCourses;

    private List<String> healthWays;

    private List<String> solutionNames;

    private List<Integer> solutionIds;

}
