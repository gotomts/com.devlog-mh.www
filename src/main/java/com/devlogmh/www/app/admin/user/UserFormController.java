package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.admin.service.users.UsersFormService;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersForm;
import com.devlogmh.www.exception.DuplicateProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * ユーザ管理フォームコントローラー
 */
@Controller
@RequestMapping("/admin/user-master")
public class UserFormController {

    /*------------ DI ---------------*/

    @Autowired
    private UsersFormService usersFormService;

    /*------------ Viewのパス設定 ---------------*/

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
     * ユーザー管理
     */
    private final String REDIRECT_USER_MASTER = "redirect:/admin/user-master/0";

    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView newUsers(UsersForm inputForm, ModelAndView mav) {
        // フォームの初期設定
        usersFormService.setupForm(inputForm);
        // オブジェクトを詰め込み
        mav.addObject("form", inputForm);
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
    public ModelAndView create(@ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav) throws DuplicateProductException {

        // フォームの初期設定
        usersFormService.setupForm(inputForm);

        // 独自バリデーションチェック実装
        usersFormService.validate(inputForm, result);

        // エラーだった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_NEW);
            // オブジェクトを詰め込み
            mav.addObject("form", inputForm);
            return mav;
        }

        // 保存処理
        usersFormService.save(inputForm);
        // リダイレクト設定
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, UsersForm inputForm, ModelAndView mav) {
        // ビューの設定
        mav.setViewName(USER_MASTER_EDIT);
        // フォームの初期設定
        usersFormService.setupForm(id, inputForm);
        // オブジェクトを詰め込み
        mav.addObject("form", inputForm);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("edit/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav) throws DuplicateProductException {

        // フォームの初期設定
        usersFormService.setupForm(inputForm);

        // エンティティ生成
        UsersDto usersDto = new UsersDto();

        usersFormService.validate(id, inputForm, result);

        // エラーだった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_EDIT);
            // オブジェクトを詰め込み
            mav.addObject("form", inputForm);
            return mav;
        }

        // 更新する対象のIDを設定
        usersDto.setId(id);

        // 更新処理
        usersFormService.update(usersDto, inputForm);

        // 更新後のリダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;
    }


}
