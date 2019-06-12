package com.devlogmh.www.admin.user;

import com.devlogmh.www.domain.admin.service.users.UsersService;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class UserServiceTest {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {

    }

    @Test
    @Transactional
    public void ユーザー情報一覧を取得できるかテスト() {
        List<UsersDto> list = usersService.init();
    }

    @Test
    @Transactional
    public void ユーザー情報をIDで検索し取得できるかテスト() {
        UsersEntity entity = usersService.findOne(Long.parseLong("1"));
        assertEquals(entity.getUserName(), "ユニットテスト用");
        assertEquals(entity.getEmail(), "mh.goto.web@gmail.com");
    }

    /**
     * 事後処理
     */
    @Before
    public void 事後処理() throws Exception {

    }


}
