package com.devlogmh.www.app.admin.category;

import com.devlogmh.www.domain.admin.service.categoryDetail.CategoryDetailCreateService;
import com.devlogmh.www.domain.admin.service.categoryDetail.CategoryDetailEditService;
import com.devlogmh.www.domain.admin.service.categoryDetail.CategoryDetailService;
import com.devlogmh.www.domain.admin.service.categoryDetail.CategoryDetailUpdateService;
import com.devlogmh.www.domain.admin.util.RedirectContains;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RedirectContains.*;
import static com.devlogmh.www.domain.admin.util.RoutesContains.*;

/**
 * カテゴリーマスタフォームコントローラー
 */
@Controller
@RequestMapping("/admin/category-master")
public class CategoryDetailController {

    /*------------ DI ---------------*/

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private CategoryControlDto categoryControlDto;

    /** 新規作成 初期表示 */
    @Autowired
    private CategoryDetailService categoryDetailService;

    /** 新規作成 登録処理 */
    @Autowired
    private CategoryDetailCreateService categoryDetailCreateService;

    /** 編集画面 初期表示 */
    @Autowired
    private CategoryDetailEditService categoryDetailEditService;

    /** 編集画面 更新処理 */
    @Autowired
    private CategoryDetailUpdateService categoryDetailUpdateService;

    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView index(CategoryForm inputForm, ModelAndView mav) {

        // カテゴリーマスタ詳細フォーム
        categoryControlDto.setCategoryForm(inputForm);
        // ModelAndView
        categoryControlDto.setMav(mav);

        // 初期表示
        categoryDetailService.delegate(categoryControlDto);

        // ビューの設定
        mav.setViewName(CATEGORY_MASTER_NEW);
        return mav;

    }

    /**
     * 新規作成 登録処理
     * @param inputForm
     * @return
     */
    @PostMapping("new")
    public ModelAndView create(@ModelAttribute("form") @Validated CategoryForm inputForm, BindingResult result, ModelAndView mav) {

        // カテゴリーマスタ詳細フォーム
        categoryControlDto.setCategoryForm(inputForm);
        // ModelAndView
        categoryControlDto.setMav(mav);
        // バリデーションエラー結果
        categoryControlDto.setBindingResult(result);

        // 新規作成 登録処理
        categoryDetailCreateService.delegate(categoryControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(CATEGORY_MASTER_NEW);
            return mav;
        }

        // リダイレクト設定
        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER);
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, CategoryForm inputForm, ModelAndView mav) {

        // カテゴリーID
        categoryControlDto.setId(id);
        // カテゴリーマスタ詳細フォーム
        categoryControlDto.setCategoryForm(inputForm);
        // ModelAndView
        categoryControlDto.setMav(mav);

        // フォームの初期設定
        categoryDetailEditService.delegate(categoryControlDto);

        // ビューの設定
        mav.setViewName(CATEGORY_MASTER_EDIT);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("edit/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated CategoryForm inputForm, BindingResult result, ModelAndView mav) {

        // カテゴリーID
        categoryControlDto.setId(id);
        // カテゴリーマスタ詳細フォーム
        categoryControlDto.setCategoryForm(inputForm);
        // ModelAndView
        categoryControlDto.setMav(mav);
        // バリデーションエラー結果
        categoryControlDto.setBindingResult(result);

        // 新規作成 登録処理
        categoryDetailUpdateService.delegate(categoryControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_CATEGORY_MASTER);
            return mav;
        }

        // 更新後のリダイレクト先
        mav = new ModelAndView(RedirectContains.REDIRECT_CATEGORY_MASTER);
        return mav;
    }


}
