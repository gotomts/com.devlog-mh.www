package com.devlogmh.www.domain.model.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class UsersEntity {

    /**
     * ユーザーID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    /**
     * ユーザー名
     */
    @Getter
    @Setter
    private String name;

    /**
     * メールアドレス
     */
    @Getter
    @Setter
    private String email;

    /**
     * パスワード
     */
    @Getter
    @Setter
    private String password;

    /**
     * アカウント種類ID
     */
    @Getter
    @Setter
    private Integer role_id;

    /**
     * 更新者
     */
    @Getter
    @Setter
    private Long updater_id;

    /**
     * 作成日時
     */
    @Getter
    @Setter
    private Timestamp created;

    /**
     * 更新日時
     */
    @Getter
    @Setter
    private Timestamp updated;

    /**
     * 削除フラグ
     */
    @Getter
    @Setter
    private Integer delflg;

}
