package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_images")
@Data
public class ImagesEntity extends BaseEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private int id;

    /**
     * 图片名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 图片大小
     */
    @Column(name = "size", columnDefinition = "int")
    private Long size;

    /**
     * 图片路径
     */
    @Column(name = "path")
    private String path;

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
