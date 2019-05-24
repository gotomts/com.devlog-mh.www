package com.devlogmh.www.app.admin.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @GetMapping("/admin/category")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/category/category-master");
        return mav;
    }

}
