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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
     * ユーザー管理一覧 パスパラメータなし表示テスト
     */
    @Test
    public void ユーザー管理一覧パスパラメータなしでアクセスできるかテスト() throws Exception {

        // ログインユーザーを偽装するためにセッションにユーザーIDを格納
        sessionData.setUserId(1);

        // GETメソッドで新規作成画面へアクセスする
        ResultActions resultActions = this.mockMvc.perform(get("/admin/user-master"));

        // リダイレクトできているか確認
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(model().hasNoErrors());

    }

    /**
     * ユーザー管理一覧 パスパラメータあり表示テスト
     */
    @Test
    public void ユーザー管理一覧パスパラメータ付きでアクセスできるかテスト() throws Exception {

        // ログインユーザーを偽装するためにセッションにユーザーIDを格納
        sessionData.setUserId(1);

        // パスパラメータを設定
        String id = "0";

        // GETメソッドで新規作成画面へアクセスする
        ResultActions resultActions = this.mockMvc.perform(get("/admin/user-master/{id}", id));

        // リダイレクトできているか確認
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(model().hasNoErrors());

    }

    /**
     * 事後処理
     */
    @After
    public void 事後処理() throws Exception {
    }

}
