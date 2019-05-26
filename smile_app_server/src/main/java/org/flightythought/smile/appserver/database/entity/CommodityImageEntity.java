package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:23
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_commodity_image")
public class CommodityImageEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 图片ID
     */
    @Column(name = "image_id")
    private Integer imageId;
}
