package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_sys_parameter")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysParameterEntity extends BaseEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "parameter_key")
    private String parameterKey;

    @Basic
    @Column(name = "parameter_value")
    private String parameterValue;

    @Basic
    @Column(name = "parameter_desc")
    private String parameterDesc;

}
