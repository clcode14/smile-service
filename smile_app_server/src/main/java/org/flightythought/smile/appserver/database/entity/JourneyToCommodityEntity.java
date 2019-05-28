package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToCommodityEntity
 * @CreateTime 2019/5/26 14:41
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_journey_to_commodity")
public class JourneyToCommodityEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 旅程ID
     */
    @Column(name = "journey_id")
    private Integer journeyId;

    /**
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Integer commodityId;
}
