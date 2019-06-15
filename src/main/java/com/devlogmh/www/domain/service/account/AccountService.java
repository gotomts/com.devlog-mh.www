package com.devlogmh.www.domain.service.account;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 初期ユーザー登録クラス
 */
@Service
public class AccountService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;

    /**
     * コンストラクタ
     * @param accountMapper
     */
    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }


    // ユーザ登録処理
    public void create(AccountEntity accountEntity, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        accountEntity.setPassword(encodedPassword);
        accountMapper.insert(accountEntity);
    }
}
