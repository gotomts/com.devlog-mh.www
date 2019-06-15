package com.devlogmh.www.admin.user;

import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.mapper.UsersMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("unit")
public class UsersRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    private final UsersMapper usersMapper;

    public UsersRepositoryTest(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {

    }

    @Test
    public void getOneメソッドのテスト() {

        UsersEntity testUser1 = new UsersEntity("ユーザー名", "test1@example.com",
                "password", 1, new Long(1),
                TimestampUtil.currentTime(), TimestampUtil.currentTime(), 0);

        UsersEntity testUser2 = new UsersEntity("山田 太郎", "test2@example.com",
                "password", 1, new Long(1),
                TimestampUtil.currentTime(), TimestampUtil.currentTime(), 0);

        entityManager.persist(testUser1);
        entityManager.persist(testUser2);
        entityManager.flush();

        UsersEntity usersEntity1 = usersMapper.select(Long.parseLong("1"));
        assertEquals(usersEntity1.getUserName(), "ユーザー名");

//        UsersEntity usersEntity2 = usersRepository.getOne(Long.parseLong("2"));
//        assertEquals(usersEntity2.getUserName(), "山田 太郎");
    }

    /**
     * 事後処理
     */
    @Before
    public void 事後処理() throws Exception {

    }


}
