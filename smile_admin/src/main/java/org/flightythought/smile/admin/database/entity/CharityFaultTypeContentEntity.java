package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_charity_fault_type_content")
@Data
@EqualsAndHashCode(callSuper = false)
public class CharityFaultTypeContentEntity extends BaseEntity {

    /**
     * 行善过失类型内容ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "cf_type_id")
    private Integer cfTypeId;

    @Column(name = "content", columnDefinition = "text")
    private String content;
}
