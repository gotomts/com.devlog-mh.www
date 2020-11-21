package com.devlogmh.www.domain.model.account;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountForm implements Serializable {

    /**
     * ユーザー名
     */
    private String name;

    /**
     * メールアドレス
     */
    private String email;

    /**
     * パスワード
     */
    private String password;

}
