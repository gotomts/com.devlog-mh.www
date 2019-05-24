package com.devlogmh.www.app.admin.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/admin/index")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/index/index");
        return mav;
    }

}
