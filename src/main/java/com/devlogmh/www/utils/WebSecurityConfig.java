package com.devlogmh.www.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ログイン認証設定クラス
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * ログイン認証の例外となるディレクトリやファイルを設定します。
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*/favicon.ico", "/*/css/**", "/*/js/**", "/*/images/**", "/*/fonts/**");
    }

    /**
     * 認証の要否となるURL・ログインしていない場合の遷移ページを設定します。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認証が必要となるURLを設定します。
        // account以下のURLも認証不要です。
        // それ以外はすべて認証を必要とします。
        http.authorizeRequests()
                .antMatchers("/admin/login/**", "/account/**").permitAll()
                .anyRequest().authenticated()

        // ログインしていないに遷移するページを設定します。
        .and()
            .formLogin()
            .loginProcessingUrl("/admin/login")
            .loginPage("/admin/login");

    }

    /**
     * パスワードをBCrypt方式のハッシュ化します。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
