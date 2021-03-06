package com.devlogmh.www.domain.admin.security;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.model.account.AccountUserDetails;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SessionData sessionData;

    /**
     * メールアドレスに一致するユーザ情報を取得
     * @param email
     * @return usersEntity
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AccountEntity accountEntity = Optional.ofNullable(accountMapper.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("user not found."));

        if (Objects.nonNull(accountEntity)) {
            this.sessionData.setUserId(accountEntity.getId().intValue());

            // ログインしたか判定
            boolean isLogin = false;
            if (Objects.nonNull(this.sessionData.getUserId())) {
                isLogin = true;
            }
            this.sessionData.setLogin(isLogin);
        }

        return new AccountUserDetails(accountEntity);

    }

}
