package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_health_norm_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthNormTypeEntity extends BaseEntity {

    /**
     * 指标ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "norm_type_id")
    private Integer normTypeId;

    /**
     * 指标编码
     */
    @Column(name = "norm_number")
    private String normNumber;

    /**
     * 指标名称
     */
    @Column(name = "norm_name")
    private String normName;

    /**
     * 单位
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 最大值
     */
    @Column(name = "max")
    private Double max;

    /**
     * 最小值
     */
    @Column(name = "min")
    private Double min;

    /**
     * 步进
     */
    @Column(name = "step")
    private Double step;

    /**
     * 输入类型（参数个数）1：普通输入类型 2：血压两个输入框
     */
    @Column(name = "input_type")
    private Integer inputType;
}
