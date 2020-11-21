package com.devlogmh.www.app.admin.profile;

import com.devlogmh.www.domain.admin.service.profile.ProfileEditService;
import com.devlogmh.www.domain.admin.service.profile.ProfileUpdateService;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_PROFILE;
import static com.devlogmh.www.domain.admin.util.RoutesContains.PROFILE_EDIT;

/**
 * プロフィール コントローラー
 */
@Controller
@RequestMapping("/admin/profile")
public class ProfileController {

    /*------------ DI ---------------*/

    /** プロフィール コントローラーからサービスへの橋渡し */
    @Autowired
    private UsersControlDto usersControlDto;

    /** 編集画面 初期表示 */
    @Autowired
    private ProfileEditService profileDetailEditService;

    /** 更新処理 */
    @Autowired
    private ProfileUpdateService profileUpdateService;

    /**
     * 編集画面 表示
     */
    @GetMapping
    public ModelAndView edit(@ModelAttribute("successMsg") String successMsg, UsersForm inputForm, ModelAndView mav) {

        // 成功メッセージ
        usersControlDto.setSuccessMsg(successMsg);
        // プロフィール フォーム
        usersControlDto.setUsersForm(inputForm);
        // ModelAndView
        usersControlDto.setMav(mav);

        // フォームの初期設定
        profileDetailEditService.delegate(usersControlDto);

        // ビューの設定
        mav.setViewName(PROFILE_EDIT);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping
    public ModelAndView update(@ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // プロフィール フォーム
        usersControlDto.setUsersForm(inputForm);
        // ModelAndView
        usersControlDto.setMav(mav);
        // バリデーションエラー結果
        usersControlDto.setBindingResult(result);
        // リダイレクト時に値を渡す
        usersControlDto.setRedirectAttributes(redirectAttributes);

        // 新規作成 登録処理
        profileUpdateService.delegate(usersControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(PROFILE_EDIT);
            return mav;
        }

        // 更新後のリダイレクト先
        mav = new ModelAndView(REDIRECT_PROFILE);
        return mav;
    }

}
