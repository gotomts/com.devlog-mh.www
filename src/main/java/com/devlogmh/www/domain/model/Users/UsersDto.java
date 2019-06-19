package com.devlogmh.www.domain.model.users;

import lombok.Data;

/**
 * ユーザー情報DTO
 */
@Data
public class UsersDto extends UsersEntity {

    /**
     * チェックボックス
     */
    private Long[] checkId;

    /**
     * ログインユーザー
     */
    private Integer userId;

    /**
     * 更新者.
     */
    private String updaterName;

    /**
     * フォーマット後の更新時間
     */
    private String updateTime;

    /**
     * エラーメッセージ
     */
    private String errorMsg;

}
