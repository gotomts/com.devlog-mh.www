package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {

    void insert(UsersEntity usersEntity);

    void update(UsersEntity usersEntity);

    UsersEntity select(Long id);

    List<UsersDto> selectAll();
}
