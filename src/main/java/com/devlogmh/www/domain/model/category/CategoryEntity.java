package com.devlogmh.www.domain.model.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CategoryEntity {

    /**
     * ユーザーID
     */
    private Long id;

    /**
     * ユーザー名
     */
    private String userName;

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
    private Long updaterId;

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

    /**
     * デフォルトコンストラクタ
     */
    public CategoryEntity(String userName, String email, String password, Integer roleId,
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
