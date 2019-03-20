package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_course_registration", schema = "smile", catalog = "")
public class CourseRegistrationEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "course_id")
    private int courseId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "start_time")
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime startTime;

    @Basic
    @Column(name = "price")
    private BigDecimal price;

    @Basic
    @Column(name = "members")
    private int members;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "cover_picture_name")
    private String coverPictureName;

    @Basic
    @Column(name = "cover_picture_path")
    private String coverPicturePath;

    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<CourseImageEntity> courseImages;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverPictureName() {
        return coverPictureName;
    }

    public void setCoverPictureName(String coverPictureName) {
        this.coverPictureName = coverPictureName;
    }

    public String getCoverPicturePath() {
        return coverPicturePath;
    }

    public void setCoverPicturePath(String coverPicturePath) {
        this.coverPicturePath = coverPicturePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CourseImageEntity> getCourseImages() {
        return courseImages;
    }

    public void setCourseImages(List<CourseImageEntity> courseImages) {
        this.courseImages = courseImages;
    }
}
