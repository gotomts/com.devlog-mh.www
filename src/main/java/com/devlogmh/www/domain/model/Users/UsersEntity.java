package com.devlogmh.www.domain.model.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@NoArgsConstructor
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
    @Column(name = "name")
    private String userName;

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

    /**
     * デフォルトコンストラクタ
     */
    public UsersEntity(String userName, String email, String password, Integer roleId,
                       Long updaterId, Timestamp created, Timestamp updated, int delflg) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.updaterId = updaterId;
        this.created = created;
        this.updated = updated;
        this.delflg = delflg;
    }
}
