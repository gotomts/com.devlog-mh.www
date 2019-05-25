package com.devlogmh.www.domain.model.Users;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Usersテーブルのエンティティ
 */
@Entity
@Table(name="users")
@Data
public class UsersEntity implements Serializable {

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

}
