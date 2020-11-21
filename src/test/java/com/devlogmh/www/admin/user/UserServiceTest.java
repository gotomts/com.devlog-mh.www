package com.devlogmh.www.admin.user;

import com.devlogmh.www.domain.admin.service.users.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class UserServiceTest {

    @Autowired
    private UsersService usersService;

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {

    }

    @Test
    @Transactional
    public void ユーザー情報一覧を取得できるかテスト() {
//        List<UsersDto> list = usersService.init();
    }

    /**
     * 事後処理
     */
    @Before
    public void 事後処理() throws Exception {

    }


}
