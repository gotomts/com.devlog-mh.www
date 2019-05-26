package com.devlogmh.www.domain.service.account;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.repository.UsersRepository;
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
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // ユーザ登録処理
    public UsersEntity create(UsersEntity usersEntity, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        usersEntity.setPassword(encodedPassword);
        return usersRepository.save(usersEntity);
    }
}
