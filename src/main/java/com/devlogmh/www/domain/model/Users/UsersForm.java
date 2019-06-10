package com.devlogmh.www.domain.model.users;

import com.devlogmh.www.domain.model.role.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * ユーザー管理フォームクラス
 */
public class UsersForm implements Serializable {

    /**
     *  ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * ユーザー名
     */
    @Getter
    @Setter
    private String userName;

    /**
     * メールアドレス
     */
    @Getter
    @Setter
    private String email;

    /**
     * ユーザー権限／ID
     */
    @Getter
    @Setter
    private Integer roleId;

    /**
     * ユーザー権限／リスト
     */
    @Getter
    @Setter
    private List<RoleEntity> roleList;

    /**
     * パスワード
     */
    @Getter
    @Setter
    private String password;

    /**
     * 更新者／ID
     */
    @Getter
    @Setter
    private Long updaterId;

    /**
     * 更新者／名称
     */
    @Getter
    @Setter
    private String updaterName;

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
     * フォーマット後の更新時間
     */
    @Getter
    @Setter
    private String updateTime;

    /**
     * 削除フラグ
     */
    @Getter
    @Setter
    private Integer delflg;

}
