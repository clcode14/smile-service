package org.flightythought.smile.appserver.database.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@ApiModel(value = "UserFollowDiseaseEntity", description = "用户关注疾病实体")
@Data
@Entity
@Table(name = "tb_user_follow_disease")
public class UserFollowDiseaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "id", strategy = "identity")
    @Column(name = "id")
    private Long id;

    @Column(name = "disease_detail_id", updatable = false, insertable = false)
    private Integer diseaseDetailId;

    @OneToOne
    @JoinColumn(name = "disease_detail_id")
    private DiseaseClassDetailEntity diseaseClassDetailEntity;

    @Column(name = "user_id")
    private Long userId;
}
