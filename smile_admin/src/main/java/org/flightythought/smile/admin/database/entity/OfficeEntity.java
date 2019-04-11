package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "tb_office")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "office_id")
    private Long officeId;

    private String name;

    private String address;

    private String description;

}
