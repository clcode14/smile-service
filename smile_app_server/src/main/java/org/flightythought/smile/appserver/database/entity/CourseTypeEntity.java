package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_course_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseTypeEntity extends BaseEntity {
    /**
     * 课程类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 课程类型名称
     */
    @Column(name = "course_type_name")
    private String courseTypeName;
}
