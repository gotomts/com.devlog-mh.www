package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.admin.service.users.*;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_USER_MASTER;
import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_USER_MASTER_TRASH;
import static com.devlogmh.www.domain.admin.util.RoutesContains.USER_MASTER;
import static com.devlogmh.www.domain.admin.util.RoutesContains.USER_MASTER_TRASH;

/**
 * ユーザ管理コントローラー
 */
@Controller
@RequestMapping("/admin/user-master")
public class UserController {

    /*------------ DI ---------------*/

    /** ユーザー管理 コントローラーからサービスへの橋渡し */
    @Autowired
    private UsersControlDto usersControlDto;

    /** ユーザー管理 一覧 */
    @Autowired
    private UsersService usersService;

    /** ユーザー管理 ゴミ箱へ追加 */
    @Autowired
    private UsersTrashAddService usersTrashAddService;

    /** ユーザー管理 ゴミ箱から戻す */
    @Autowired
    private UsersTrashRemoveService usersTrashRemoveService;

    /** ユーザー管理 ゴミ箱一覧 */
    @Autowired
    private UsersDeleteListService usersDeleteListService;

    /** ユーザー管理 ゴミ箱削除 */
    @Autowired
    private UsersTrashDestroyService usersTrashDestroyService;

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav) {

        // パスパラメータ付きの一覧URLへリダイレクト
        mav.setViewName(REDIRECT_USER_MASTER);
        return mav;

    }

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg, @PathVariable int id, ModelAndView mav) {

        // エラーメッセージ
        usersControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        usersControlDto.setPathNum(id);
        // ModelAndView
        usersControlDto.setMav(mav);

        // 一覧表示処理
        usersService.delegate(usersControlDto);

        // ビューの設定
        mav.setViewName(USER_MASTER);
        return mav;

    }

    /**
     * ゴミ箱へ移動
     */
    @PostMapping("trash-add")
    public ModelAndView trashAdd(@ModelAttribute("pagedListHolder") @Validated UsersListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        usersControlDto.setUsersListForm(inputForm);
        // バリデーションエラー結果
        usersControlDto.setBindingResult(result);
        // ModelAndView
        usersControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        usersControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        usersTrashAddService.delegate(usersControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_USER_MASTER);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;

    }

    /**
     * ゴミ箱から戻す
     */
    @PostMapping("trash-remove")
    public ModelAndView trashRemove(@ModelAttribute("pagedListHolder") @Validated UsersListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        usersControlDto.setUsersListForm(inputForm);
        // バリデーションエラー結果
        usersControlDto.setBindingResult(result);
        // ModelAndView
        usersControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        usersControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        usersTrashRemoveService.delegate(usersControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_USER_MASTER_TRASH);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;
    }


    /**
     * ゴミ箱を見る リダイレクト
     */
    @GetMapping("trash-list")
    public ModelAndView deleteList(ModelAndView mav) {
        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER_TRASH);
        return mav;
    }

    /**
     * ゴミ箱 一覧
     */
    @GetMapping("trash-list/{id}")
    public ModelAndView deleteList(@ModelAttribute("errorMsg") String errorMsg, @PathVariable int id, ModelAndView mav) {

        // エラーメッセージ
        usersControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        usersControlDto.setPathNum(id);
        // ModelAndView
        usersControlDto.setMav(mav);

        // 一覧表示処理
        usersDeleteListService.delegate(usersControlDto);

        // ビューの設定
        mav.setViewName(USER_MASTER_TRASH);
        return mav;

    }

    /**
     * ゴミ箱のチェックしたデータを削除
     */
    @PostMapping("trash-destroy")
    public ModelAndView trashDestroy(@ModelAttribute("pagedListHolder") @Validated UsersListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        usersControlDto.setUsersListForm(inputForm);
        // バリデーションエラー結果
        usersControlDto.setBindingResult(result);
        // ModelAndView
        usersControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        usersControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        usersTrashDestroyService.delegate(usersControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_USER_MASTER_TRASH);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER_TRASH);
        return mav;
    }

}
