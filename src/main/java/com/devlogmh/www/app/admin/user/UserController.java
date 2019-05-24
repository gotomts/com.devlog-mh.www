package com.devlogmh.www.app.admin.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/admin/user")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/user/user-master");
        return mav;
    }

}
