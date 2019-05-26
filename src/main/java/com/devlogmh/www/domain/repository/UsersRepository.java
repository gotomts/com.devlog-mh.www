package com.devlogmh.www.domain.repository;

import com.devlogmh.www.domain.model.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザエンティティのリポジトリインターフェース
 */
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    /**
     * メールアドレスをキーにユーザエンティティを検索します。
     * @param email
     * @return UsersEntity
     */
    UsersEntity findByEmail(String email);
}
