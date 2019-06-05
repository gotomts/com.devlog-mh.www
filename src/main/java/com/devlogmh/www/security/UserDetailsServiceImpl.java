package com.devlogmh.www.security;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * メールアドレスに一致するユーザ情報を取得
     * @param email
     * @return usersEntity
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UsersEntity usersEntity = usersRepository.findByEmail(email);
        return usersEntity;

    }
}
