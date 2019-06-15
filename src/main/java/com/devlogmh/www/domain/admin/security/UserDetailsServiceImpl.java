package com.devlogmh.www.domain.admin.security;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.mapper.AccountMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountMapper accountMapper;

    /**
     * コンストラクタ
     * @param accountMapper
     */
    public UserDetailsServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    /**
     * メールアドレスに一致するユーザ情報を取得
     * @param email
     * @return usersEntity
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AccountEntity accountEntity = accountMapper.findByEmail(email);
        return accountEntity;

    }
}
