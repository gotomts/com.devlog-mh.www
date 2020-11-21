package com.devlogmh.www.domain.model.account;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Usersテーブルのエンティティ
 */
@Data
public class AccountEntity implements UserDetails {

    private static final long serialVersionUID = 2L;

    /**
     * ユーザーID
     */
    private Integer id;

    /**
     * ユーザー名
     */
    private String name;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * パスワード
     */
    private String password;

    /**
     * アカウント種類ID
     */
    private Integer roleId;

    /**
     * 更新者
     */
    private Integer updaterId;

    /**
     * 作成日時
     */
    private Timestamp created;

    /**
     * 更新日時
     */
    private Timestamp updated;

    /**
     * 削除フラグ
     */
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
