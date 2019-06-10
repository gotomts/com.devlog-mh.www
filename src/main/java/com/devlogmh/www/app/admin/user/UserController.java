package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.model.role.RoleEntity;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.model.users.UsersForm;
import com.devlogmh.www.domain.service.usersService.UsersFormService;
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

    /*------------ DI ---------------*/

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersFormService usersFormService;

    /*------------ フォーム ---------------*/

    private UsersForm inputForm;

    /*------------ Viewのパス設定 ---------------*/

    /**
     * 一覧
     */
    private final String USER_MASTER = "app/admin/user/user-master";

    /**
     * 新規登録画面
     */
    private final String USER_MASTER_NEW = "app/admin/user/user-master-new";

    /**
     * 編集画面
     */
    private final String USER_MASTER_EDIT = "app/admin/user/user-master-edit";

    /**
     * リダイレクト
     */
    private final String USER_MASTER_REDIRECT = "redirect:/admin/user-master";


    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav) {
        // ビューの設定
        mav.setViewName(USER_MASTER);
        // サービスの初期処理
        List<UsersDto> dtoList = usersService.init();
        // オブジェクトを詰め込み
        mav.addObject("dto", dtoList);
        return mav;
    }

    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView newUsers(@ModelAttribute("form") UsersEntity usersEntity, ModelAndView mav) {
        mav.setViewName(USER_MASTER_NEW);
        // フォームの初期設定
        this.inputForm = usersFormService.setupForm();
        // オブジェクトを詰め込み
        mav.addObject("form", this.inputForm);
        return mav;
    }

    /**
     * 新規作成 登録処理
     * @param usersEntity
     * @return
     */
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute("form") @Validated UsersEntity usersEntity, BindingResult result, ModelAndView mav) {

        // フォームの初期設定
        this.inputForm = usersFormService.setupForm(usersEntity);

        // エラーだった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_NEW);
            // オブジェクトを詰め込み
            mav.addObject("form", this.inputForm);
            result.getTarget();
            return mav;
        }

        // 保存処理
        usersFormService.save(this.inputForm);
        // リダイレクト設定
        mav = new ModelAndView(USER_MASTER_REDIRECT);
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav) {
        // ビューの設定
        mav.setViewName(USER_MASTER_EDIT);
        // フォームの初期設定
        this.inputForm = usersFormService.setupForm(id);
        // オブジェクトを詰め込み
        mav.addObject("form", this.inputForm);
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
            mav.setViewName(USER_MASTER_EDIT);
            return mav;
        }
        usersEntity.setId(id);
        usersService.save(usersEntity);
        mav = new ModelAndView(USER_MASTER_REDIRECT);
        return mav;
    }

    /**
     * 削除処理
     */
    @DeleteMapping("destroy/{id}")
    public ModelAndView destroy(@PathVariable Long id, ModelAndView mav) {
        usersService.delete(id);
        mav = new ModelAndView(USER_MASTER_REDIRECT);
        return mav;
    }


}
