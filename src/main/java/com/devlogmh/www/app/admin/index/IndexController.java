package com.devlogmh.www.app.admin.index;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.service.index.IndexService;
import com.devlogmh.www.security.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * 管理画面トップコントローラークラス
 * @author goto
 */
@Controller
@RequestMapping("/admin")
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
    public ModelAndView init(ModelAndView mav, Principal principal) {
        // 参照するHTML
        mav.setViewName("app/admin/index/index");

        // Principalからログインユーザの情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // ユーザ情報の取得
        AccountEntity accountEntity = indexService.findByEmail(email);

        this.sessionData.setUserId(accountEntity.getId().intValue());

        mav.addObject("entity", accountEntity);

        return mav;
    }

}
