package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteEntity
 * @CreateTime 2019/4/10 2:12
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_note")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyNoteEntity extends BaseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 养生旅程ID
     */
    @Column(name = "journey_id")
    private Integer journeyId;

    /**
     * 封面图片ID
     */
    @Column(name = "cover_image_id")
    private Integer coverImageId;

    @OneToOne
    @JoinColumn(name = "cover_image_id",insertable = false,updatable = false)
    private ImagesEntity coverImage;

    /**
     * 日记内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 是否发送到朋友圈
     */
    @Column(name = "circle_of_friends")
    private Boolean circleOfFriends;

    /**
     * 日记时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "note_date")
    private LocalDateTime noteDate;

}
