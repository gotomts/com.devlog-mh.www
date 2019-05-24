package com.devlogmh.www.app.admin.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/admin/login")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/login/login");
        return mav;
    }

}
