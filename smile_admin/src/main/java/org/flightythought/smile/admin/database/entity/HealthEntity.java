package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.flightythought.smile.admin.database.entity.BaseEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthEntity
 * @CreateTime 2019/4/9 17:43
 * @Description: TODO
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_health")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthEntity extends BaseEntity {

    /**
     * 养生ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "health_id")
    private Integer healthId;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;

    /**
     * 养生大类名称
     */
    @Column(name = "health_name")
    private String healthName;

    /**
     * 背景图片ID
     */
    @Column(name = "bg_image")
    private Integer bgImageId;

    /**
     * 背景图
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bg_image", insertable = false, updatable = false)
    private ImagesEntity bgImage;

    /**
     * 内容介紹
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 养生关联的解决方案
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_health_to_solution", joinColumns = {@JoinColumn(name = "health_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "solution_id", nullable = false, updatable = false)})
    private List<SolutionEntity> solutions;

}
