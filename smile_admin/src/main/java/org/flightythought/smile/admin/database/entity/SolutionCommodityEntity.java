package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:51
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_solution_commodity")
public class SolutionCommodityEntity {

    /**
     * 自增主键
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
     * 商品ID
     */
    @Column(name = "commodity_id")
    private Integer commodityId;
}
