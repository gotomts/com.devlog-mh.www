package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.admin.service.usersDetail.UsersDetailCreateService;
import com.devlogmh.www.domain.admin.service.usersDetail.UsersDetailEditService;
import com.devlogmh.www.domain.admin.service.usersDetail.UsersDetailService;
import com.devlogmh.www.domain.admin.service.usersDetail.UsersDetailUpdateService;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_USER_MASTER;
import static com.devlogmh.www.domain.admin.util.RoutesContains.USER_MASTER_EDIT;
import static com.devlogmh.www.domain.admin.util.RoutesContains.USER_MASTER_NEW;

/**
 * ユーザ管理フォームコントローラー
 */
@Controller
@RequestMapping("/admin/user-master")
public class UserDetailController {

    /*------------ DI ---------------*/

    /** ユーザー管理 コントローラーからサービスへの橋渡し */
    @Autowired
    private UsersControlDto usersControlDto;

    /** 新規作成 初期表示 */
    @Autowired
    private UsersDetailService usersDetailService;

    /** 新規作成 登録処理 */
    @Autowired
    private UsersDetailCreateService usersDetailCreateService;

    /** 編集画面 初期表示 */
    @Autowired
    private UsersDetailEditService usersDetailEditService;

    /** 編集画面 更新処理 */
    @Autowired
    private UsersDetailUpdateService usersDetailUpdateService;

    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView index(UsersForm inputForm, ModelAndView mav) {

        // ユーザー管理詳細フォーム
        usersControlDto.setUsersForm(inputForm);
        // ModelAndView
        usersControlDto.setMav(mav);

        // 初期表示
        usersDetailService.delegate(usersControlDto);

        // ビューの設定
        mav.setViewName(USER_MASTER_NEW);
        return mav;

    }

    /**
     * 新規作成 登録処理
     * @param inputForm
     * @return
     */
    @PostMapping("new")
    public ModelAndView create(@ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav) {

        // ユーザー管理詳細フォーム
        usersControlDto.setUsersForm(inputForm);
        // ModelAndView
        usersControlDto.setMav(mav);
        // バリデーションエラー結果
        usersControlDto.setBindingResult(result);

        // 新規作成 登録処理
        usersDetailCreateService.delegate(usersControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_NEW);
            return mav;
        }

        // リダイレクト設定
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, UsersForm inputForm, ModelAndView mav) {

        // ユーザーID
        usersControlDto.setUserId(id);
        // ユーザー管理詳細フォーム
        usersControlDto.setUsersForm(inputForm);
        // ModelAndView
        usersControlDto.setMav(mav);

        // フォームの初期設定
        usersDetailEditService.delegate(usersControlDto);

        // ビューの設定
        mav.setViewName(USER_MASTER_EDIT);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("edit/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav) {

        // ユーザーID
        usersControlDto.setUserId(id);
        // ユーザー管理詳細フォーム
        usersControlDto.setUsersForm(inputForm);
        // ModelAndView
        usersControlDto.setMav(mav);
        // バリデーションエラー結果
        usersControlDto.setBindingResult(result);

        // 新規作成 登録処理
        usersDetailUpdateService.delegate(usersControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_EDIT);
            return mav;
        }

        // 更新後のリダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;
    }


}
