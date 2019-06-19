package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "tb_user")
@ApiModel(value = "UserEntity", description = "user实体")
public class UserEntity extends BaseEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Long id;

    /**
     * 手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * AuthId
     */
    @Column(name = "auth_id")
    private String authId;

    /**
     * 第三方登陆类型
     */
    @Column(name = "third_type")
    private String thirdType;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 登陆时间
     */
    @Column(name = "login_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    /**
     * 登陆次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * 照片头像
     */
    @Column(name = "photo")
    private Integer photo;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo", insertable = false, updatable = false)
    private ImagesEntity photoImage;

    /**
     * IP地址
     */
    @Column(name = "ip")
    @JsonIgnore
    private String ip;

    /**
     * Token
     */
    @Column(name = "token")
    private String token;

    /**
     * 身高
     */
    @Column(name = "height")
    private Double height;

    /**
     * 体重
     */
    @Column(name = "body_weight")
    private Double bodyWeight;

    /**
     * 生日
     */
    @Column(name = "birthday")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 头像
     */
    @Column(name = "avater")
    private String avater;

    /**
     * 性别 0：男 1：女
     */
    @Column(name = "sex")
    private Integer sex;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
