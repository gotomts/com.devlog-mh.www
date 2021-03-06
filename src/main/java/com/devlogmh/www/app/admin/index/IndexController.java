package com.devlogmh.www.app.admin.index;

import com.devlogmh.www.domain.admin.service.index.IndexService;
import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.users.UsersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_LOGIN;
import static com.devlogmh.www.domain.admin.util.RoutesContains.ADMIN_INDEX;

/**
 * 管理画面トップコントローラークラス
 * @author goto
 */
@Controller
@RequestMapping("/admin")
@SessionAttributes(types = UsersForm.class)
public class IndexController {

    @Autowired
    IndexService indexService;

    @Autowired
    SessionData sessionData;

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav, Principal principal) {

        // ログイン確認
        if (!this.sessionData.isLogin()) {
            mav.setViewName(REDIRECT_LOGIN);
            return mav;
        }

        // Principalからログインユーザの情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // ユーザ情報の取得
        AccountEntity accountEntity = indexService.findByEmail(email);

        accountEntity.setId(sessionData.getUserId());

        mav.addObject("entity", accountEntity);

        mav.addObject("isLogin", this.sessionData.isLogin());

        // 参照するHTML
        mav.setViewName(ADMIN_INDEX);
        return mav;
    }

}
