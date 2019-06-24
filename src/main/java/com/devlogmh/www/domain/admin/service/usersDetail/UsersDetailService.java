package com.devlogmh.www.domain.admin.service.usersDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersForm;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional
public class UsersDetailService extends AbsUtilService {

    @Autowired
    private SessionData sessionData;

    /**
     * パスワード暗号化処理
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    private UsersForm usersForm;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.usersForm = usersControlDto.getUsersForm();
        this.mav = usersControlDto.getMav();

        // ログイン情報を格納
        this.mav.addObject("isLogin", this.sessionData.isLogin());

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // フォームの初期設定
        this.setupForm(this.usersForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.usersForm);

    }

    /**
     * フォームに入力された値を保持してフォームの初期設定
     * @param inputForm
     * @return
     */
    public void setupForm(UsersForm inputForm) {

        // ユーザー名
        inputForm.setUserName(inputForm.getUserName());
        // メールアドレス
        inputForm.setEmail(inputForm.getEmail());
        // アカウント種類
        inputForm.setRoleId(inputForm.getRoleId());
        // アカウント種類リスト
        inputForm.setRoleList(inputForm.getSelectRoleItems());
        // パスワード
        inputForm.setPassword(inputForm.getPassword());

    }

}
