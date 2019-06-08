package com.devlogmh.www.domain.repository;

import com.devlogmh.www.domain.model.users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

}
