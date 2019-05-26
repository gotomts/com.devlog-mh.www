package com.devlogmh.www.domain.model.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginForm implements Serializable {

    /**
     * メールアドレス
     */
    private String email;

    /**
     * パスワード
     */
    private String password;

}
