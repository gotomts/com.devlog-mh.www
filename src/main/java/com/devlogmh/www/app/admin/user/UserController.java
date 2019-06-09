package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.model.role.RoleEntity;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.service.usersService.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public ModelAndView newUsers(ModelAndView mav) {
        mav.setViewName("app/admin/user/user-master-new");

        List<RoleEntity> roleList = usersService.roleList();
        mav.addObject("form", roleList);

        UsersEntity usersEntity = new UsersEntity();
        mav.addObject("usersEntity", usersEntity);

        return mav;
    }

    /**
     * 新規作成 登録処理
     * @param usersEntity
     * @return
     */
    @PostMapping("create")
    public String create(@Valid @ModelAttribute UsersEntity usersEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "app/admin/user/user-master-new";
        }
        usersService.save(usersEntity);
        return "redirect:/admin/user-master";
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav) {
        mav.setViewName("app/admin/user/user-master-edit");
        List<RoleEntity> roleList = usersService.roleList();
        UsersEntity usersEntity = usersService.findOne(id);
        mav.addObject("form", usersEntity);
        mav.addObject("roleList", roleList);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute UsersEntity usersEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "app/admin/user/user-master-edit";
        }
        usersEntity.setId(id);
        usersService.save(usersEntity);
        return "redirect:/admin/user-master";
    }

    /**
     * 削除処理
     */
    @DeleteMapping("destroy/{id}")
    public String destroy(@PathVariable Long id) {
        usersService.delete(id);
        return "redirect:/admin/user-master";
    }


}
