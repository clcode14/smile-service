package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_images")
@Data
@EqualsAndHashCode(callSuper = false)
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
