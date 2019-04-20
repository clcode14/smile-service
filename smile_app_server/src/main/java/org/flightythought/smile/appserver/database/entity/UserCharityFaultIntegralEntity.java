package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_charity_fault_integral")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserCharityFaultIntegralEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "charity_count")
    private Integer charityCount;

    @Column(name = "fault_count")
    private Integer faultCount;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    public UserCharityFaultIntegralEntity() {
    }

    public UserCharityFaultIntegralEntity(Long userId) {
        this.userId = userId;
        this.charityCount = 0;
        this.faultCount = 0;
        this.score = 0;
        this.createUserName = userId + "";
    }
}
