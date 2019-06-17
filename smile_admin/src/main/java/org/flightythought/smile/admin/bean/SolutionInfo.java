package org.flightythought.smile.admin.bean;

import java.util.List;

import lombok.Data;

@Data
public class SolutionInfo {

    private Integer id;

    private String number;

    private String title;

    private String content;

    private List<String> refCourses;

    private List<String> refOffices;
    
    private List<String> refCommodities;

    private Integer recoverNumber;

    private List<ImageInfo> images;
}
