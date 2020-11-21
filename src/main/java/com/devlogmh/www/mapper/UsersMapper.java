package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.users.UsersDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersMapper {

    void insert(UsersDto usersDto);

    void update(UsersDto usersDto);

    UsersDto select(Long id);

    List<UsersDto> selectAll();

    List<UsersDto> selectUserList(@Param("loginId") int loginId, @Param("delflg") int delflg);

    void trashMove(UsersDto usersDto);

    void destroy(UsersDto usersDto);

}
