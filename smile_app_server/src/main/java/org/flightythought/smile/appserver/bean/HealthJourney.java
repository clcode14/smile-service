package org.flightythought.smile.appserver.bean;

import lombok.Data;
import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;

import java.util.List;

@Data
public class HealthJourney extends HealthJourneySimple {
    /**
     * 关联的疾病小类
     */
    private List<DiseaseClassDetailSimple> diseaseClassDetails;

    /**
     * 关联的养生方式
     */
    private List<HealthClass> healthClasses;

    /**
     * 养生结果
     */
    private List<HealthResultSimple> healthResult;

    /**
     * 旅程体检指标
     */
    private List<JourneyNorm> journeyNorm;

    /**
     * 体检指标
     */
    private List<HealthNormTypeEntity> healthNormTypes;

    /**
     * 旅程开始体检报告
     */
    private List<FileInfo> startReports;

    /**
     * 旅程结束体检报告
     */
    private List<FileInfo> endReports;
}
