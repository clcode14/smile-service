package org.flightythought.smile.admin.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "r_user_role")
@Data
@IdClass(UserRolePrimaryKey.class)
@EqualsAndHashCode(callSuper = false)
public class UserRoleEntity implements Serializable{

    /**  */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 权限ID
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;
}
