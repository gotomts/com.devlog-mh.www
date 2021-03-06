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

import static com.devlogmh.www.domain.admin.util.RedirectContains.*;
import static com.devlogmh.www.domain.admin.util.RoutesContains.*;

/**
 * カテゴリーコントローラー
 */
@Controller
@RequestMapping("/admin/category-master")
public class CategoryController {

    /*------------ DI ---------------*/

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private CategoryControlDto categoryControlDto;

    /** カテゴリーマスタ 一覧 */
    @Autowired
    private CategoryService categoryService;

    /** ゴミ箱へ追加 */
    @Autowired
    private CategoryTrashAddService categoryTrashAddService;

    /** ゴミ箱から戻す */
    @Autowired
    private CategoryTrashRemoveService categoryTrashRemoveService;

    /** ゴミ箱一覧 */
    @Autowired
    private CategoryTrashListService categoryTrashListService;

    /** ゴミ箱削除 */
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
        this.categoryControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        this.categoryControlDto.setPathNum(id);
        // ModelAndView
        this.categoryControlDto.setMav(mav);

        // 一覧表示処理
        this.categoryService.delegate(this.categoryControlDto);

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
        categoryControlDto.setCategoryListForm(inputForm);
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
        categoryControlDto.setCategoryListForm(inputForm);
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
        categoryControlDto.setCategoryListForm(inputForm);
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
