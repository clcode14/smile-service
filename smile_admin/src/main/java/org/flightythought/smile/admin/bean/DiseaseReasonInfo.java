package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

@Data
public class DiseaseReasonInfo {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 疾病大类名称
     */
    private String diseaseName;

    /**
     * 疾病小类名称
     */
    private String diseaseDetailName;

    /**
     * 编码
     */
    private String number;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型
     */
    private SelectItemOption type;

    /**
     * 解决方案名称
     */
    private List<SelectItemOption> solutionNames;

}
