package com.devlogmh.www.domain.model.users;

import lombok.Data;

/**
 * ユーザー管理一覧
 */
@Data
public class UsersListForm {

    /**
     * ゴミ箱 戻す／移動<br>
     * 0 戻す / 1 移動
     */
    private int delflg;

    /**
     * チェックボックス
     */
    private Long[] checkId;

}
