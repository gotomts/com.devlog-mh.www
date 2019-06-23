package com.devlogmh.www.app.admin.category;

import com.devlogmh.www.domain.admin.service.category.*;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_CATEGORY_MASTER;
import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_CATEGORY_MASTER_TRASH;
import static com.devlogmh.www.domain.admin.util.RoutesContains.CATEGORY_MASTER;
import static com.devlogmh.www.domain.admin.util.RoutesContains.CATEGORY_MASTER_TRASH;

/**
 * ユーザ管理コントローラー
 */
@Controller
@RequestMapping("/admin/category-master")
public class CategoryController {

    /*------------ DI ---------------*/

    /** ユーザー管理 コントローラーからサービスへの橋渡し */
    @Autowired
    private CategoryControlDto categoryControlDto;

    /** ユーザー管理 一覧 */
    @Autowired
    private CategoryService categoryService;

    /** ユーザー管理 ゴミ箱へ追加 */
    @Autowired
    private CategoryTrashAddService categoryTrashAddService;

    /** ユーザー管理 ゴミ箱から戻す */
    @Autowired
    private CategoryTrashRemoveService categoryTrashRemoveService;

    /** ユーザー管理 ゴミ箱一覧 */
    @Autowired
    private CategoryTrashListService categoryTrashListService;

    /** ユーザー管理 ゴミ箱削除 */
    @Autowired
    private CategoryTrashDestroyService categoryTrashDestroyService;

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping({"", "{id}"})
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg, @PathVariable(name = "id", required = false) String id, ModelAndView mav) {

        // エラーメッセージ
        categoryControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        categoryControlDto.setPathNum(id);
        // ModelAndView
        categoryControlDto.setMav(mav);

        // 一覧表示処理
        categoryService.delegate(categoryControlDto);

        // ビューの設定
        mav.setViewName(CATEGORY_MASTER);
        return mav;

    }

    /**
     * ゴミ箱へ移動
     */
    @PostMapping("trash-add")
    public ModelAndView trashAdd(@ModelAttribute("pagedListHolder") @Validated CategoryListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        categoryControlDto.setUsersListForm(inputForm);
        // バリデーションエラー結果
        categoryControlDto.setBindingResult(result);
        // ModelAndView
        categoryControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        categoryControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        categoryTrashAddService.delegate(categoryControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_CATEGORY_MASTER);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER);
        return mav;

    }

    /**
     * ゴミ箱から戻す
     */
    @PostMapping("trash-remove")
    public ModelAndView trashRemove(@ModelAttribute("pagedListHolder") @Validated CategoryListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        categoryControlDto.setUsersListForm(inputForm);
        // バリデーションエラー結果
        categoryControlDto.setBindingResult(result);
        // ModelAndView
        categoryControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        categoryControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        categoryTrashRemoveService.delegate(categoryControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_CATEGORY_MASTER_TRASH);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER);
        return mav;
    }


    /**
     * ゴミ箱 一覧
     */
    @GetMapping({"trash-list", "trash-list/{id}"})
    public ModelAndView deleteList(@ModelAttribute("errorMsg") String errorMsg, @PathVariable(name = "id", required = false) String id, ModelAndView mav) {

        // エラーメッセージ
        categoryControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        categoryControlDto.setPathNum(id);
        // ModelAndView
        categoryControlDto.setMav(mav);

        // 一覧表示処理
        categoryTrashListService.delegate(categoryControlDto);

        // ビューの設定
        mav.setViewName(CATEGORY_MASTER_TRASH);
        return mav;

    }

    /**
     * ゴミ箱のチェックしたデータを削除
     */
    @PostMapping("trash-destroy")
    public ModelAndView trashDestroy(@ModelAttribute("pagedListHolder") @Validated CategoryListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        categoryControlDto.setUsersListForm(inputForm);
        // バリデーションエラー結果
        categoryControlDto.setBindingResult(result);
        // ModelAndView
        categoryControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        categoryControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        categoryTrashDestroyService.delegate(categoryControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_CATEGORY_MASTER_TRASH);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER_TRASH);
        return mav;
    }

}
