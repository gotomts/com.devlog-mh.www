package com.devlogmh.www.admin.user;

import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unit")
public class UsersFormTest {

    /**
     * バリデータ
     */
    private Validator validator;

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {
        // バリデータを初期化
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     *
     */
    @Test
    @Transactional
    public void userNameの妥当性テスト() throws Exception {
        UsersForm usersForm = new UsersForm();
        usersForm.setId(Long.parseLong("1"));
        // わざと255文字以上にする
        usersForm.setUserName("ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名ユーザーテスト名");
        usersForm.setEmail("test1@gmail.com");
        usersForm.setRoleId(1);
        usersForm.setPassword("password");
        usersForm.setUpdaterId(Long.parseLong("1"));
        usersForm.setCreated(TimestampUtil.currentTime());
        usersForm.setUpdated(TimestampUtil.currentTime());

        // バリデート
        Set<ConstraintViolation<UsersForm>> violations = validator.validate(usersForm);

        // エラーの数は1つか
        assertEquals(violations.size(), 1);

    }

    /**
     * 事後処理
     */
    @After
    public void 事後処理() throws Exception {

    }


}
