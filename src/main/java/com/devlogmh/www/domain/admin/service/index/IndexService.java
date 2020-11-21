package com.devlogmh.www.domain.admin.service.index;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
 * 管理画面トップ サービスクラス
 */
@Service
public class IndexService {

    private final AccountMapper accountMapper;

    public IndexService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    /**
     * メールアドレスをキーにユーザ情報を取得
     * @param email
     * @return
     */
    public AccountEntity findByEmail(String email) {
        return accountMapper.findByEmail(email);
    }

}
