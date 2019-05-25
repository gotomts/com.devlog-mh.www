package com.devlogmh.www.domain.repository;

import com.devlogmh.www.domain.model.Users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザーテーブルのリポジトリインターフェース
 */
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
}
