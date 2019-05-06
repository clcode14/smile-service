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
 * @ClassName SolutionImageEntity
 * @CreateTime 2019/3/27 17:17
 * @Description: 课程图片实体类
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_solution_image")
@Data
@EqualsAndHashCode(callSuper = false)
public class SolutionImageEntity {
    /**
     * 图片主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 解决方案ID
     */
    @Column(name = "solution_id")
    private Integer solutionId;

    /**
     * 图片ID
     */
    @Column(name = "image_id")
    private Integer imageId;
}
