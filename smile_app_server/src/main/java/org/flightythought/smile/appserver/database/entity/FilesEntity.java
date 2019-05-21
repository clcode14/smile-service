package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_files")
@Data
@EqualsAndHashCode(callSuper = false)
public class FilesEntity extends BaseEntity {


    /**
     * 文件ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @Column(name = "size", columnDefinition = "int")
    private Long size;

    /**
     * 文件路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 文件类型
     */
    @Column(name = "file_type")
    private String fileType;

    /**
     * 所属模块
     */
    @Column(name = "module")
    private String module;

    /**
     * OSS URL
     */
    @Column(name = "oss_url")
    private String ossUrl;

    /**
     * OSS KEY
     */
    @Column(name = "oss_key")
    private String ossKey;
}
