package com.devlogmh.www.admin.user;

import com.devlogmh.www.domain.model.session.SessionData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
@ActiveProfiles("unit")
public class UserControllerTest {

    /**
     * アプリケーションの設定などを管理するコンテキスト
     */
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private GenericApplicationContext applicationContext;

    @Autowired
    private SessionData sessionData;

    /**
     * リクエストとレスポンスとそれに付随する情報のモックオブジェクト
     */
    private MockMvc mockMvc;

    /**
     * 事前処理
     */
    @Before
    public void 事前処理() throws Exception {
        // モックを初期化
        this.mockMvc = webAppContextSetup(wac).build();

        // sessionDataに値を格納できるようにする
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        Scope sessionScope = new SimpleThreadScope();
        beanFactory.registerScope("session", sessionScope);

    }

    /**
     * ユーザー管理一覧の初期表示のテスト
     */
    @Test
    public void ユーザー管理一覧の初期表示のテスト() throws Exception {
        // GETメソッドで新規作成画面へアクセスする
        this.mockMvc.perform(get("/admin/user-master"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    /**
     * ユーザー管理新規作成の初期表示のテスト
     */
    @Test
    public void ユーザー管理新規作成の初期表示のテスト() throws Exception {
        // GETメソッドで新規作成画面へアクセスする
        this.mockMvc.perform(get("/admin/user-master/new"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors());
    }

    /**
     * ユーザー管理新規作成の初期表示のテスト
     */
    @Test
    public void ユーザー管理新規作成の登録処理のテスト() throws Exception {

        // sessionDataにユーザーをセット
        sessionData.setUserId(1);

        // POSTメソッドでアクセスし、入力画面で入力した値として各種パラメータを付加する
        ResultActions resultActions = this.mockMvc.perform(post("/admin/user-master/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("userName", "ユーザーテスト")
                .param("email", "test1@example.com")
                .param("password", "password")
                .param("roleId", "1")
        );

        // レスポンスの検証
        // 登録が成功し、リダイレクトしたかを確認
        resultActions.andExpect(status().is3xxRedirection())
            .andExpect(model().hasNoErrors());

    }

    /**
     * 事後処理
     */
    @After
    public void 事後処理() throws Exception {
    }

}
