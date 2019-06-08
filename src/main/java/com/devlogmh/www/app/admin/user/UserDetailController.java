package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.service.usersService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ユーザー管理詳細コントローラー
 */
@Controller
@RequestMapping("/admin/user-master-detail")
public class UserDetailController {

    @Autowired
    private UsersService usersService;

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView init(ModelAndView mav) {
        mav.setViewName("app/admin/user/user-master-edit");
        return mav;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute UsersEntity usersEntity) {
        usersService.save(usersEntity);
        return "redirect:/admin/user-master";
    }

}
