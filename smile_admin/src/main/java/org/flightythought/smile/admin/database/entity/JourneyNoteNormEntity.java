package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteNormEntity
 * @CreateTime 2019/4/10 2:12
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_note_norm")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyNoteNormEntity extends BaseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 体检指标类型ID
     */
    @Column(name = "norm_type_id")
    private Integer normTypeId;

    /**
     * 日记ID
     */
    @Column(name = "note_id")
    private Integer noteId;

    /**
     * 数值1
     */
    @Column(name = "value1")
    private String value1;

    /**
     * 数值2
     */
    @Column(name = "value2")
    private String value2;

}
