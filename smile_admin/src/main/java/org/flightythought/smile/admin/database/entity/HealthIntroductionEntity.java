package org.flightythought.smile.admin.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 
 * 
 * @author cl47872
 * @version $Id: EncourageEntity.java, v 0.1 Jun 8, 2019 9:00:56 PM cl47872 Exp $
 */
@Data
@Entity
@Table(name = "tb_health_introduction")
public class HealthIntroductionEntity extends BaseEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

}
