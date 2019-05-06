package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseBannerEntity
 * @CreateTime 2019/5/2 22:47
 * @Description: TODO
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_course_banner")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseBannerEntity extends BaseEntity {
    /**
     * 自增主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 状态
     */
    @Column(name = "status")
    private Boolean status;


    /**
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;
}
