package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_charity_fault_record_image")
@Data
@EqualsAndHashCode(callSuper = false)
public class CharityFaultRecordImageEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "charity_fault_record_id")
    private Integer charityFaultRecordId;
}
