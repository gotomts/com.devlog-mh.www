package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.model.role.RoleEntity;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.service.usersService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * ユーザ管理コントローラー
 */
@Controller
@RequestMapping("/admin/user-master")
public class UserController {

    @Autowired
    private UsersService usersService;

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav) {
        List<UsersDto> dto = usersService.findAll();
        mav.addObject("dto", dto);
        mav.setViewName("app/admin/user/user-master");
        return mav;
    }

    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView newUsers(@ModelAttribute("usersEntity") UsersEntity usersEntity, ModelAndView mav) {
        mav.setViewName("app/admin/user/user-master-new");

        List<RoleEntity> roleList = usersService.roleList();
        mav.addObject("roleList", roleList);
        mav.addObject("usersEntity", usersEntity);

        return mav;
    }

    /**
     * 新規作成 登録処理
     * @param usersEntity
     * @return
     */
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute("usersEntity") @Validated UsersEntity usersEntity, BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            List<RoleEntity> roleList = usersService.roleList();
            mav.addObject("roleList", roleList);
            mav.setViewName("app/admin/user/user-master-new");
            return mav;
        }
        usersService.save(usersEntity);
        mav = new ModelAndView("redirect:/admin/user-master");
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav) {
        mav.setViewName("app/admin/user/user-master-edit");
        List<RoleEntity> roleList = usersService.roleList();
        UsersEntity usersEntity = usersService.findOne(id);
        mav.addObject("usersEntity", usersEntity);
        mav.addObject("roleList", roleList);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("update/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("usersEntity") @Validated UsersEntity usersEntity, BindingResult result, ModelAndView mav) {
        if (result.hasErrors()) {
            List<RoleEntity> roleList = usersService.roleList();
            mav.addObject("roleList", roleList);
            mav.setViewName("app/admin/user/user-master-edit");
            return mav;
        }
        usersEntity.setId(id);
        usersService.save(usersEntity);
        mav = new ModelAndView("redirect:/admin/user-master");
        return mav;
    }

    /**
     * 削除処理
     */
    @DeleteMapping("destroy/{id}")
    public ModelAndView destroy(@PathVariable Long id, ModelAndView mav) {
        usersService.delete(id);
        mav = new ModelAndView("redirect:/admin/user-master");
        return mav;
    }


}
