package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author lilei
 */
@Entity
@Table(name = "tb_sys_user")
public class SysUserEntity extends BaseEntity implements UserDetails {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private int id;

    /**
     * 登录名
     */
    @Basic
    @Column(name = "login_name", nullable = false)
    private String loginName;

    /**
     * 用户名
     */
    @Basic
    @Column(name = "user_name", nullable = false)
    private String userName;

    /**
     * 密码
     */
    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 部门ID
     */
    @Basic
    @Column(name = "department_id", insertable = false, updatable = false)
    private Integer departmentId;

    /**
     * 帐号状态
     */
    @Basic
    @Column(name = "status")
    private boolean status;

    /**
     * 登录限制（1：允许登录；0：限制登录）
     */
    @Basic
    @Column(name = "login_limit")
    private Integer loginLimit;

    /**
     * 过期时间（NULL表示永不过期）
     */
    @Basic
    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;

    /**
     * 邮箱
     */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * 手机号码
     */
    @Basic
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 电话
     */
    @Basic
    @Column(name = "tel")
    private String tel;

    /**
     * 部门（通过department_id字段关联表m_organization）
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private SysDepartmentEntity department;

    /**
     * 权限
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "r_user_role", joinColumns = {@JoinColumn(name = "user_id", nullable = false, updatable = false)}
            , inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)})
    private Set<SysRoleEntity> roles = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户权限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(sysRoleEntity -> authorities.add(new SimpleGrantedAuthority(sysRoleEntity.getRole())));
        return authorities;
    }

    /**
     * 获取用户密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取登录用户名
     */
    @Override
    public String getUsername() {
        return loginName;
    }

    /**
     * 判断帐号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        if (expirationTime == null) {
            // 过期时间为NULL，表示永不过期
            return true;
        } else {
            return LocalDateTime.now().isBefore(expirationTime);
        }
    }

    /**
     * 判断帐号是否被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return loginLimit == 1;
    }

    /**
     * 用户凭证是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 帐号是否可用
     */
    @Override
    public boolean isEnabled() {
        return status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setState(boolean status) {
        this.status = status;
    }

    public Integer getLoginLimit() {
        return loginLimit;
    }

    public void setLoginLimit(Integer loginLimit) {
        this.loginLimit = loginLimit;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Set<SysRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRoleEntity> roles) {
        this.roles = roles;
    }

    public SysDepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(SysDepartmentEntity department) {
        this.department = department;
    }
}
