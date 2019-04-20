package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_dictionary")
@Data
@EqualsAndHashCode(callSuper = false)
public class DictionaryEntity extends BaseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 字典分类
     */
    @Column(name = "dic_category")
    private String dicCategory;

    /**
     * 字典名称
     */
    @Column(name = "dic_name")
    private String dicName;

    /**
     * Key值
     */
    @Column(name = "dic_key")
    private String dicKey;

    /**
     * Value值
     */
    @Column(name = "dic_value")
    private String dicValue;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
}
