package com.devlogmh.www.app.admin.post;

import com.devlogmh.www.domain.admin.service.postDetail.PostDetailCreateService;
import com.devlogmh.www.domain.admin.service.postDetail.PostDetailEditService;
import com.devlogmh.www.domain.admin.service.postDetail.PostDetailService;
import com.devlogmh.www.domain.admin.service.postDetail.PostDetailUpdateService;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_POST_LIST;
import static com.devlogmh.www.domain.admin.util.RoutesContains.POST_EDIT;
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

    /** 新規作成 登録処理 */
    @Autowired
    private PostDetailCreateService postDetailCreateService;

    /** 編集画面 初期表示 */
    @Autowired
    private PostDetailEditService postDetailEditService;

    /** 編集画面 更新処理 */
    @Autowired
    private PostDetailUpdateService postDetailUpdateService;

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

    /**
     * 新規作成 登録処理
     * @param inputForm
     * @return
     */
    @PostMapping("new")
    public ModelAndView create(@ModelAttribute("form") @Validated PostForm inputForm, BindingResult result, ModelAndView mav) {

        // カテゴリーマスタ詳細フォーム
        postControlDto.setPostForm(inputForm);
        // ModelAndView
        postControlDto.setMav(mav);
        // バリデーションエラー結果
        postControlDto.setBindingResult(result);

        // 新規作成 登録処理
        postDetailCreateService.delegate(postControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(POST_NEW);
            return mav;
        }

        // リダイレクト設定
        mav = new ModelAndView(REDIRECT_POST_LIST);
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, PostForm inputForm, ModelAndView mav) {

        // カテゴリーID
        postControlDto.setId(id);
        // カテゴリーマスタ詳細フォーム
        postControlDto.setPostForm(inputForm);
        // ModelAndView
        postControlDto.setMav(mav);

        // フォームの初期設定
        postDetailEditService.delegate(postControlDto);

        // ビューの設定
        mav.setViewName(POST_EDIT);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("edit/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated PostForm inputForm, BindingResult result, ModelAndView mav) {

        // カテゴリーID
        postControlDto.setId(id);
        // カテゴリーマスタ詳細フォーム
        postControlDto.setPostForm(inputForm);
        // ModelAndView
        postControlDto.setMav(mav);
        // バリデーションエラー結果
        postControlDto.setBindingResult(result);

        // 新規作成 登録処理
        postDetailUpdateService.delegate(postControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(POST_EDIT);
            return mav;
        }

        // 更新後のリダイレクト先
        mav = new ModelAndView(REDIRECT_POST_LIST);
        return mav;
    }


}
