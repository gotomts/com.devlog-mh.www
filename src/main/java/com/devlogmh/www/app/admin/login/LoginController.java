package com.devlogmh.www.app.admin.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.LOGIN;

/**
 * ログイントップコントローラークラス
 * @author goto
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName(LOGIN);
        return mav;
    }

}
