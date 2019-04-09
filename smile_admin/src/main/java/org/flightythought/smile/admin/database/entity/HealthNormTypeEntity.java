package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_health_norm_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthNormTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "norm_type_id")
    private Integer normTypeId;

    @Column(name = "norm_number")
    private String normNumber;

    @Column(name = "norm_name")
    private String normName;
}
