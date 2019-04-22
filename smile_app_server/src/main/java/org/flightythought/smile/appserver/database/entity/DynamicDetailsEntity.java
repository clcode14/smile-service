package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_dynamic_details")
@Data
@EqualsAndHashCode(callSuper = false)
public class DynamicDetailsEntity extends BaseEntity {
    /**
     * 动态明细ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "dynamic_detail_id")
    private Integer dynamicDetailId;

    /**
     * 动态ID
     */
    @Column(name = "dynamic_id")
    private Integer dynamicId;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 转发个数
     */
    @Column(name = "forward_num")
    private Integer forwardNum;

    /**
     * 评论个数
     */
    @Column(name = "message_num")
    private Integer messageNum;

    /**
     * 点赞个数
     */
    @Column(name = "like_num")
    private Integer likeNum;

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
     * 点赞的用户
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_to_dynamic_detail_like", joinColumns = {@JoinColumn(name = "dynamic_detail_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)})
    private List<UserEntity> likeUsers;

    /**
     * 动图明细文件
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_dynamic_details_files", joinColumns = {@JoinColumn(name = "dynamic_detail_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "file_id", nullable = false, updatable = false)})
    private List<FilesEntity> files;
}
