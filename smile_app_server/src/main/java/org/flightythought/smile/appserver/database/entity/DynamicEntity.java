package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_dynamic")
@Data
@EqualsAndHashCode(callSuper = false)
public class DynamicEntity extends BaseEntity {
    /**
     * 动态ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "dynamic_id")
    private Integer dynamicId;

    /**
     * 动态标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 动态内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    /**
     * 动态个数
     */
    @Column(name = "dynamic_detail_num")
    private Integer dynamicDetailNum;

    /**
     * 浏览数
     */
    @Column(name = "read_num")
    private Integer readNum;

    /**
     * 是否隐藏
     */
    @Column(name = "hidden")
    private Boolean hidden;

    /**
     * 是否是热门动态：0 普通动态 1 热门动态
     */
    @Column(name = "hot")
    private Integer hot;

    /**
     * 动态上传的文件
     */
    @ManyToMany
    @JoinTable(name = "tb_dynamic_files", joinColumns = {@JoinColumn(name = "dynamic_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "file_id", nullable = false, updatable = false)})
    private List<FilesEntity> files;

    /**
     * 动态明细
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "dynamic_id", insertable = false, updatable = false)
    private List<DynamicDetailsEntity> dynamicDetails;

}
