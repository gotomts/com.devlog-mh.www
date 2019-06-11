package com.devlogmh.www.domain.admin.service.index;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 管理画面トップ サービスクラス
 */
@Service
@Transactional
public class IndexService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * メールアドレスをキーにユーザ情報を取得
     * @param email
     * @return
     */
    public AccountEntity findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

}
