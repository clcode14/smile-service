package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_charity_fault_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class CharityFaultTypeEntity extends BaseEntity {

    /**
     * 行善过失类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "cf_type_id")
    private Integer cfTypeId;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 行善过失类型，0:行善，1:过失
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 积分
     */
    @Column(name = "integral")
    private Integer integral;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "cf_type_id")
    private List<CharityFaultTypeContentEntity> charityFaultTypeContentEntities;
}
