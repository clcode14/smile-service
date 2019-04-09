package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "tb_disease_reason_type")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class DiseaseReasonTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "type_name")
    private String typeName;
}
