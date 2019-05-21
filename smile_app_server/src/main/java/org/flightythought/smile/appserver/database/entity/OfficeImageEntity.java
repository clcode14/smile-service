package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeImageEntity
 * @CreateTime 2019/5/21 23:41
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_office_image")
public class OfficeImageEntity {

    /**
     * 自增主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Long id;

    /**
     * 机构ID
     */
    @Column(name = "office_id")
    private Long officeId;

    /**
     * 图片ID
     */
    @Column(name = "image_id")
    private Integer imageId;
}
