package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionImage
 * @CreateTime 2019/3/27 17:17
 * @Description: 课程图片实体类
 */
@Entity
@Table(name = "tb_solution_image")
@Data
@EqualsAndHashCode(callSuper = false)
public class SolutionImage extends BaseEntity {
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
     * 图片路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 图片URL
     */
    @Column(name = "url")
    private String url;

    /**
     * 图片名称
     */
    @Column(name = "image_name")
    private String imageName;

    /**
     * 图片大小
     */
    @Column(name = "size", columnDefinition = "int")
    private Long size;

}
