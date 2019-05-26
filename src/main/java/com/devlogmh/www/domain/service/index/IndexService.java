package com.devlogmh.www.domain.service.index;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 管理画面トップ サービスクラス
 */
@Service
@Transactional
public class IndexService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * メールアドレスをキーにユーザ情報を取得
     * @param email
     * @return
     */
    public UsersEntity findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}
