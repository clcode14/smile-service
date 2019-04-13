package org.flightythought.smile.admin.bean;

import lombok.Data;
import org.flightythought.smile.admin.database.entity.OfficeEntity;

import java.util.List;

@Data
public class SolutionInfo {

    private Integer id;

    private String number;

    private String title;

    private String content;

    private List<String> refCourses;

    private List<String> refOffices;

    private Integer recoverNumber;

    private List<ImageInfo> images;
}
