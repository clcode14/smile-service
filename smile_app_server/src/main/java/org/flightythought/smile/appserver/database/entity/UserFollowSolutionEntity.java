package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tb_user_follow_solution")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserFollowSolutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "id", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "solution_id")
    private Integer diseaseDetailId;

    @OneToOne
    @JoinColumn(name = "solution_id", insertable = false, updatable = false)
    private SolutionEntity solution;

    @Column(name = "user_id")
    private Long userId;
}
