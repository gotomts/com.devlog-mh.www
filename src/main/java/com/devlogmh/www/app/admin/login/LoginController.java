//package com.devlogmh.www.app.admin.login;
//
//import com.devlogmh.www.domain.model.login.LoginForm;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * ログイントップコントローラークラス
// * @author goto
// */
//@Controller
//public class LoginController {
//
//
//    /**
//     * 初期表示
//     * @param mav
//     * @return
//     */
//    @GetMapping("/admin/login")
//    public ModelAndView init(ModelAndView mav) {
//        mav.setViewName("app/admin/login/login");
//        return mav;
//    }
//
//    /**
//     * フォームに入力された値を取得します。
//     */
//    @ModelAttribute
//    public LoginForm setupForm() {
//        return new LoginForm();
//    }
//
//    @PostMapping("/admin/login")
//    public ModelAndView login() {
//        ModelAndView mav = new ModelAndView("redirect:/admin/index");
//        return mav;
//    }
//
//
//}
