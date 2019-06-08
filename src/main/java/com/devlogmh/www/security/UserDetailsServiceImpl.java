package com.devlogmh.www.security;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * メールアドレスに一致するユーザ情報を取得
     * @param email
     * @return usersEntity
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AccountEntity accountEntity = accountRepository.findByEmail(email);
        return accountEntity;

    }
}
