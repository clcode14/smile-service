package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeEntity
 * @CreateTime 2019/5/21 22:53
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_office")
public class OfficeEntity extends BaseEntity {

    /**
     * 相关机构ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "office_id")
    private Long officeId;

    /**
     * 机构名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 机构地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 机构描述
     */
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * 编码
     */
    @Column(name = "number")
    private String number;

    /**
     * 联系人
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 电环
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 图片
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_office_image", joinColumns = {@JoinColumn(name = "office_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<ImagesEntity> images;
}
