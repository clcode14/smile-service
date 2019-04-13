package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

@Data
public class DiseaseReasonInfo {

    private Integer id;

    private String diseaseName;

    private String diseaseDetailName;

    private String number;

    private String title;

    private String content;

    private List<String> solutionNames;

}
