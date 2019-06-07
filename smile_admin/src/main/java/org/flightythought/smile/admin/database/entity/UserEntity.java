package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_user")
@ApiModel(value = "UserEntity", description = "user实体")
public class UserEntity extends BaseEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "id", strategy = "identity")
    @Column(name = "id")
    private Long id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "nick_name")
    private String nickName;
    
    @Column(name = "recommender_id")
    private Long recommenderId;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "login_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    @Column(name = "login_count")
    private Integer loginCount;

    @Column(name = "photo")
    private Integer photo;

    @Column(name = "ip")
    @JsonIgnore
    private String ip;

    @Column(name = "token")
//    @JsonIgnore
    private String token;

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
