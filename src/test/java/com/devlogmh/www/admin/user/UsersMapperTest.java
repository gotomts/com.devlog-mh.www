package com.devlogmh.www.admin.user;

import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.mapper.UsersMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("unit")
public class UsersMapperTest {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {

    }

    @Test
    @Transactional
    public void selectメソッドのテスト() {

        UsersEntity testUser1 = new UsersEntity("ユーザー名", "test1@example.com",
                "password", 1, new Long(1),
                TimestampUtil.currentTime(), TimestampUtil.currentTime(), 0);

        UsersEntity testUser2 = new UsersEntity("山田 太郎", "test2@example.com",
                "password", 1, new Long(1),
                TimestampUtil.currentTime(), TimestampUtil.currentTime(), 0);

        GeneratedKeyHolder keyHolder1 = new GeneratedKeyHolder();
        GeneratedKeyHolder keyHolder2 = new GeneratedKeyHolder();

        update(testUser1, keyHolder1);
        update(testUser2, keyHolder2);

        UsersEntity usersEntity1 = usersMapper.select(new Long(keyHolder1.getKey().intValue()));
        assertEquals(usersEntity1.getUserName(), "ユーザー名");

        UsersEntity usersEntity2 = usersMapper.select(new Long(keyHolder2.getKey().intValue()));
        assertEquals(usersEntity2.getUserName(), "山田 太郎");
    }

    /**
     * 事後処理
     */
    @Before
    public void 事後処理() throws Exception {

    }

    public int update(UsersEntity usersEntity, GeneratedKeyHolder keyHolder) {

        int result;

        result = jdbcOperations.update(
                "INSERT INTO users(name, email, password, role_id, created, updated, delflg) VALUES(:userName, :email, :password, :roleId, :created, :updated, :delflg)",
                new BeanPropertySqlParameterSource(usersEntity), keyHolder, new String[] { "id" });

        return result;

    }


}
