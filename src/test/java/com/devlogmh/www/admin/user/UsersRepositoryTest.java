package com.devlogmh.www.admin.user;

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

import static com.devlogmh.www.domain.admin.util.TimestampUtil.currentTime;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {
        UsersEntity usersEntity1 = new UsersEntity();
        usersEntity1.setId(Long.parseLong("99999998"));
        usersEntity1.setUserName("ユーザー名");
        usersEntity1.setEmail("example@unittest1.com");
        usersEntity1.setPassword("password");
        usersEntity1.setRoleId(1);
        usersEntity1.setUpdaterId(Long.parseLong("1"));
        usersEntity1.setCreated(currentTime());
        usersEntity1.setUpdated(currentTime());
        usersEntity1.setDelflg(0);
        usersRepository.save(usersEntity1);

        UsersEntity usersEntity2 = new UsersEntity();
        usersEntity2.setId(Long.parseLong("99999999"));
        usersEntity2.setUserName("山田 太郎");
        usersEntity2.setEmail("yamda@unittest.com");
        usersEntity2.setPassword("arunb98saf&");
        usersEntity2.setRoleId(2);
        usersEntity2.setUpdaterId(Long.parseLong("1"));
        usersEntity2.setCreated(currentTime());
        usersEntity2.setUpdated(currentTime());
        usersEntity2.setDelflg(0);
        usersRepository.save(usersEntity2);
    }

    @Test
    @Transactional
    public void getOneメソッドのテスト() {
        UsersEntity usersEntity = usersRepository.getOne(Long.parseLong("1"));
        assertEquals(usersEntity.getUserName(), "ユニットテスト用");
    }

    /**
     * 事後処理
     */
    @Before
    public void 事後処理() throws Exception {

    }


}
