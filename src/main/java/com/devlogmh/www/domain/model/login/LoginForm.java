package com.devlogmh.www.domain.model.login;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class LoginForm implements Serializable {

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

}
