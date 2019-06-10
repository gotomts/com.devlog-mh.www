package com.devlogmh.www.domain.model.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotEmpty
    @Size(max = 255)
    @Column(name = "name")
    private String userName;

    /**
     * メールアドレス
     */
    @Getter
    @Setter
    @NotEmpty
    @Size(max = 255)
    @Email
    private String email;

    /**
     * パスワード
     */
    @Getter
    @Setter
    @NotEmpty
    private String password;

    /**
     * アカウント種類ID
     */
    @Getter
    @Setter
    @Min(1)
    @Max(3)
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 更新者
     */
    @Getter
    @Setter
    @Column(name = "updater_id")
    private Long updaterId;

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
