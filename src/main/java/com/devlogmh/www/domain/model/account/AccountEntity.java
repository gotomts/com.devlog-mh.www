package com.devlogmh.www.domain.model.account;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Usersテーブルのエンティティ
 */
@Entity
@Table(name="users")
@Data
public class AccountEntity implements UserDetails {

    /**
     * ユーザーID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ユーザー名
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * メールアドレス
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * パスワード
     */
    @Column(nullable = false, length = 20)
    private String password;

    /**
     * アカウント種類ID
     */
    @Column(nullable = true)
    private Integer role_id;

    /**
     * 更新者
     */
    @Column(nullable = true)
    private Integer updater_id;

    /**
     * 作成日時
     */
    @Column(nullable = true)
    private Timestamp created;

    /**
     * 更新日時
     */
    @Column(nullable = true)
    private Timestamp updated;

    /**
     * 削除フラグ
     */
    @Column(nullable = true)
    private Integer delflg;

    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /* (非 Javadoc)
     *  @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /* (非 Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
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
