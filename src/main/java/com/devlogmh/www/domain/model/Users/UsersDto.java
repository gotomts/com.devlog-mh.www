package com.devlogmh.www.domain.model.users;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー情報DTO
 */
public class UsersDto extends UsersEntity {

    /**
     * ユーザ情報名称.
     */
    @Getter
    @Setter
    String updaterName;

    /**
     * フォーマット後の更新時間
     */
    @Getter
    @Setter
    String updateTime;

}
