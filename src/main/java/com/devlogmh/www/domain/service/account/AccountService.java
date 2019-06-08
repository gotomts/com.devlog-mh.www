package com.devlogmh.www.domain.service.account;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 初期ユーザー登録クラス
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // ユーザ登録処理
    public AccountEntity create(AccountEntity accountEntity, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        accountEntity.setPassword(encodedPassword);
        return accountRepository.save(accountEntity);
    }
}
