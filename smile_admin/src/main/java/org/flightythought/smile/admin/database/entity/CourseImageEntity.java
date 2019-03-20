package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_course_image", schema = "smile", catalog = "")
public class CourseImageEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "course_image_id")
    private int courseImageId;

    @Basic
    @Column(name = "course_id")
    private int courseId;

    @Basic
    @Column(name = "file_name")
    private String fileName;

    @Basic
    @Column(name = "path")
    private String path;

    @Basic
    @Column(name = "size")
    private Double size;


    public int getCourseImageId() {
        return courseImageId;
    }

    public void setCourseImageId(int courseImageId) {
        this.courseImageId = courseImageId;
    }


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

}
