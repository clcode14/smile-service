package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_dynamic_details_files")
@Data
@EqualsAndHashCode(callSuper = false)
public class DynamicDetailsFilesEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "file_id")
    private Integer fileId;

    /**
     * 动态明细ID
     */
    @Column(name = "dynamic_detail_id")
    private Integer dynamicDetailId;
}
