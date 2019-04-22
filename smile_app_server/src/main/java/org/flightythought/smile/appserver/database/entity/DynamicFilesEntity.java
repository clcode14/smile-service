package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_dynamic_files")
@Data
@EqualsAndHashCode(callSuper = false)
public class DynamicFilesEntity {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 动态ID
     */
    @Column(name = "dynamic_id")
    private Integer dynamicId;

    /**
     * 文件ID
     */
    @Column(name = "file_id")
    private Integer fileId;
}
