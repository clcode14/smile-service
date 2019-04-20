package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteToImageEntity
 * @CreateTime 2019/4/10 23:13
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_note_to_image")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyNoteToImageEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 日记ID
     */
    @Column(name = "note_id")
    private Integer noteId;

    /**
     * 图片ID
     */
    @Column(name = "image_id")
    private Integer imageId;
}
