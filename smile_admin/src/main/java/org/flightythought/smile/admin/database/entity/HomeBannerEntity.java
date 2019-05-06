package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_home_banner")
@Data
@EqualsAndHashCode(callSuper = false)
public class HomeBannerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    private String title;

    private Integer sort;

    private String link;

    @Column(name = "image_id")
    private Integer imageId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    public ImagesEntity imagesEntity;

}
