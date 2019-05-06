package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Table(name = "tb_office")
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = false)
public class OfficeEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "office_id")
    private Long officeId;

    /**
     * 机构名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 机构编号
     */
    private String number;

    /**
     * 机构联系人
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 机构图片
     */
    @ManyToMany
    @JoinTable(name = "tb_office_image", joinColumns = {@JoinColumn(name = "office_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<ImagesEntity> officeImages;

    /**
     * 地址
     */
    private String address;

    /**
     * 描述
     */
    private String description;

}
