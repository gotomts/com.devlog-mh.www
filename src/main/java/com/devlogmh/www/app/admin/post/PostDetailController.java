package com.devlogmh.www.app.admin.post;

import com.devlogmh.www.domain.admin.service.postDetail.PostDetailService;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.POST_NEW;

/**
 * 投稿記事フォームコントローラー
 */
@Controller
@RequestMapping("/admin/post")
public class PostDetailController {

    /*------------ DI ---------------*/

    /** カテゴリーマスタ コントローラーからサービスへの橋渡し */
    @Autowired
    private PostControlDto postControlDto;

    /** 新規作成 初期表示 */
    @Autowired
    private PostDetailService postDetailService;

//    /** 新規作成 登録処理 */
//    @Autowired
//    private CategoryDetailCreateService categoryDetailCreateService;
//
//    /** 編集画面 初期表示 */
//    @Autowired
//    private CategoryDetailEditService categoryDetailEditService;
//
//    /** 編集画面 更新処理 */
//    @Autowired
//    private CategoryDetailUpdateService categoryDetailUpdateService;

    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView index(PostForm inputForm, ModelAndView mav) {

        // カテゴリーマスタ詳細フォーム
        postControlDto.setPostForm(inputForm);
        // ModelAndView
        postControlDto.setMav(mav);

        // 初期表示
        postDetailService.delegate(postControlDto);

        // ビューの設定
        mav.setViewName(POST_NEW);
        return mav;

    }

//    /**
//     * 新規作成 登録処理
//     * @param inputForm
//     * @return
//     */
//    @PostMapping("new")
//    public ModelAndView create(@ModelAttribute("form") @Validated CategoryForm inputForm, BindingResult result, ModelAndView mav) {
//
//        // カテゴリーマスタ詳細フォーム
//        postControlDto.setCategoryForm(inputForm);
//        // ModelAndView
//        postControlDto.setMav(mav);
//        // バリデーションエラー結果
//        postControlDto.setBindingResult(result);
//
//        // 新規作成 登録処理
//        categoryDetailCreateService.delegate(postControlDto);
//
//        // エラーがあった場合
//        if (result.hasErrors()) {
//            // ビューの設定
//            mav.setViewName(CATEGORY_MASTER_NEW);
//            return mav;
//        }
//
//        // リダイレクト設定
//        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER);
//        return mav;
//    }
//
//    /**
//     * 編集画面 表示
//     */
//    @GetMapping("edit/{id}")
//    public ModelAndView edit(@PathVariable Long id, CategoryForm inputForm, ModelAndView mav) {
//
//        // カテゴリーID
//        postControlDto.setId(id);
//        // カテゴリーマスタ詳細フォーム
//        postControlDto.setCategoryForm(inputForm);
//        // ModelAndView
//        postControlDto.setMav(mav);
//
//        // フォームの初期設定
//        categoryDetailEditService.delegate(postControlDto);
//
//        // ビューの設定
//        mav.setViewName(CATEGORY_MASTER_EDIT);
//        return mav;
//    }
//
//    /**
//     * 編集画面 更新処理
//     */
//    @PutMapping("edit/{id}")
//    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated CategoryForm inputForm, BindingResult result, ModelAndView mav) {
//
//        // カテゴリーID
//        postControlDto.setId(id);
//        // カテゴリーマスタ詳細フォーム
//        postControlDto.setCategoryForm(inputForm);
//        // ModelAndView
//        postControlDto.setMav(mav);
//        // バリデーションエラー結果
//        postControlDto.setBindingResult(result);
//
//        // 新規作成 登録処理
//        categoryDetailUpdateService.delegate(postControlDto);
//
//        // エラーがあった場合
//        if (result.hasErrors()) {
//            // ビューの設定
//            mav.setViewName(REDIRECT_CATEGORY_MASTER);
//            return mav;
//        }
//
//        // 更新後のリダイレクト先
//        mav = new ModelAndView(RedirectContains.REDIRECT_CATEGORY_MASTER);
//        return mav;
//    }


}