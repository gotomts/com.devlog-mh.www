package com.devlogmh.www.app.admin.index;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * 管理画面トップコントローラークラス
 * @author goto
 */
@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("/admin/index")
    public ModelAndView init(ModelAndView mav, Principal principal) {
        // 参照するHTML
        mav.setViewName("app/admin/index/index");

        // Principalからログインユーザの情報を取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        // ユーザ情報の取得
        UsersEntity usersEntity = indexService.findByEmail(email);
        mav.addObject("entity", usersEntity);

        return mav;
    }

}
