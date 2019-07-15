package com.devlogmh.www.app.admin.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.LOGIN;

/**
 * ログイントップコントローラークラス
 * @author goto
 */
@Controller
public class LoginController {

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("/admin/login")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName(LOGIN);
        return mav;
    }

}
