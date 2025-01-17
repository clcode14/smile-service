package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Table(name = "tb_disease_reason")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class DiseaseReasonEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "disease_id")
    private Integer diseaseId;

    @Column(name = "disease_detail_id")
    private Integer diseaseDetailId;

    @Column(name = "number")
    private String number;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "read_num")
    private Integer readNum;

    @Column(name = "type")
    private Integer type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_disease_reason_to_solution", joinColumns = {@JoinColumn(name = "disease_reason_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "solution_id", nullable = false, updatable = false)})
    private List<SolutionEntity> solutions;
}
