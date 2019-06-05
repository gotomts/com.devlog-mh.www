package com.devlogmh.www.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ログイン認証クラス<br>
 * 入力されたパスワードに対してBCryptでハッシュ化し、入力値が正しいかを認証します。
 */
@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
