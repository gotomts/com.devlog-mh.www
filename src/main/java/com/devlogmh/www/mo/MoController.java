package com.devlogmh.www.mo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MoController {

    @RequestMapping("/mo")
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }
}
