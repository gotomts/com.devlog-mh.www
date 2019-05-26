package com.devlogmh.www.utils.config;

import com.devlogmh.www.utils.common.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * ログイン認証設定クラス
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * パスワードをBCrypt方式のハッシュ化します。
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    /**
     * ログイン認証の例外となるディレクトリやファイルを設定します。
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/*/favicon.ico","/*/css/**",
                "/*/js/**", "/*/images/**", "/*/fonts/**",
                "/node_modules/**");
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
                .anyRequest().authenticated()
                .and()

        // ログイン処理のパスを設定します。
        .formLogin()
            .loginPage("/admin/login")  // ログインフォーム
            .loginProcessingUrl("/admin/sign_in") // 認証処理を起動
            .failureUrl("/admin/login?error")  // ログイン処理失敗時の遷移先
            .defaultSuccessUrl("/admin/index")  // 認証成功時の遷移先
            .usernameParameter("email").passwordParameter("password")   // ユーザ名とパラメータの設定
            .permitAll()
            .and()

        // ログアウト処理のパスを設定します。
        .logout()
            .logoutUrl("/admin/logout")
            .logoutSuccessUrl("/admin/login?logout")
            .permitAll();
    }

    /**
     * 認証にuserDetailsServiceを用いることを明示
     * @param auth
     * @throws Exception
     */
    public void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
