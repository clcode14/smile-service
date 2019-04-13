package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    private String number;

    private String contactName;

    private String phone;

    @ManyToMany
    @JoinTable(name = "tb_office_image", joinColumns = {@JoinColumn(name = "office_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<ImagesEntity> officeImages;

    private String address;

    private String description;

}
