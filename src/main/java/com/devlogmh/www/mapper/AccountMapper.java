package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.account.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    void insert(AccountEntity accountEntity);

    AccountEntity findByEmail(String email);

}
