package com.devlogmh.www.app.admin.index;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理画面トップコントローラークラス
 * @author goto
 */
@Controller
public class IndexController {

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("/admin/index")
    public ModelAndView init(ModelAndView mav) {
        // 参照するHTML
        mav.setViewName("app/admin/index/index");

        // Principalからログインユーザの情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        mav.addObject("userName", userName);

        return mav;
    }

}
