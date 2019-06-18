package com.devlogmh.www.domain.model.users;

import lombok.Data;

/**
 * ユーザー情報DTO
 */
@Data
public class UsersDto extends UsersEntity {

    /**
     * ログインユーザー
     */
    private Integer userId;

    /**
     * ユーザ情報名称.
     */
    private String updaterName;

    /**
     * フォーマット後の更新時間
     */
    private String updateTime;

}
