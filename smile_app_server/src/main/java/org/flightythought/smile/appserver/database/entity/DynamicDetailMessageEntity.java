package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_dynamic_detail_message")
@Data
@EqualsAndHashCode(callSuper = false)
public class DynamicDetailMessageEntity extends BaseEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 动态明细ID
     */
    @Column(name = "dynamic_detail_id")
    private Integer dynamicDetailId;

    /**
     * 评论内容
     */
    @Column(name = "message")
    private String message;

    /**
     * 父级ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 创建者用户ID
     */
    @Column(name = "from_user_id")
    private Long fromUserId;

    /**
     * 接受者用户ID
     */
    @Column(name = "to_user_id")
    private Long toUserId;

    /**
     * 接收者是否阅读
     */
    @Column(name = "read")
    private Boolean read;

    /**
     * 父级信息
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", updatable = false, insertable = false)
    private DynamicDetailMessageEntity parentMessage;

    /**
     * 下级信息
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMessage")
    private List<DynamicDetailMessageEntity> childMessage;

    /**
     * 发送者用户
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", updatable = false, insertable = false)
    private UserEntity fromUser;

    /**
     * 接收者用户
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", updatable = false, insertable = false)
    private UserEntity toUser;

}
