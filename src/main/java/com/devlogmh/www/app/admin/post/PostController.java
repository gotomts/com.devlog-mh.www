package com.devlogmh.www.app.admin.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

    @GetMapping("/admin/post")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/post/post");
        return mav;
    }

}
