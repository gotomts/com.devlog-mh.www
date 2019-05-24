package com.devlogmh.www.app.admin.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @GetMapping("/admin/profile")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/profile/profile-edit");
        return mav;
    }

}
