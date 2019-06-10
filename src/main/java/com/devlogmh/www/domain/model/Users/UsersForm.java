package com.devlogmh.www.domain.model.users;

import com.devlogmh.www.util.Contains;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ユーザー管理フォームクラス
 */
public class UsersForm implements Serializable {

    /**
     *  ユーザーID
     */
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
     * ユーザー権限／ID
     */
    @Getter
    @Setter
    @NotNull
    private Integer roleId;

    /**
     * ユーザー権限／リスト
     */
    @Setter
    @Getter
    private Map<String, String> roleList;

    /**
     * パスワード
     */
    @Getter
    @Setter
    @NotEmpty
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

    @Getter
    @Setter
    private String selectedItem;

    @Getter
    @Setter
    private String[] selectedItems;

    public Map<String, String> getSelectRoleItems() {
        Map<String, String> selectMap = new LinkedHashMap<String, String>();
        selectMap.put(Contains.RoleList.UNSELECTED.getId(), Contains.RoleList.UNSELECTED.getName());
        selectMap.put(Contains.RoleList.ADMIN.getId(), Contains.RoleList.ADMIN.getName());
        selectMap.put(Contains.RoleList.USER.getId(), Contains.RoleList.USER.getName());
        return selectMap;
    }

}
