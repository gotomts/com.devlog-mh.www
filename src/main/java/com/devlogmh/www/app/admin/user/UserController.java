package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.service.usersService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * ユーザ管理コントローラー
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UsersService usersService;

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("user-master")
    public ModelAndView init(ModelAndView mav) {
        List<UsersDto> dto = usersService.findAll();
        mav.addObject("dto", dto);
        mav.setViewName("app/admin/user/user-master");
        return mav;
    }

}
