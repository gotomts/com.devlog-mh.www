package com.devlogmh.www.app.admin.post;

import com.devlogmh.www.domain.admin.service.post.PostService;
import com.devlogmh.www.domain.admin.service.post.PostTrashAddService;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_POST_LIST;
import static com.devlogmh.www.domain.admin.util.RoutesContains.POST_LIST;

/**
 * 投稿記事管理コントローラー
 */
@Controller
@RequestMapping("/admin/post")
public class PostController {

    /*------------ DI ---------------*/

    /** 投稿記事管理 コントローラーからサービスへの橋渡し */
    @Autowired
    private PostControlDto postControlDto;

    /** 投稿記事管理 一覧 */
    @Autowired
    private PostService postService;

    /** ゴミ箱へ追加 */
    @Autowired
    private PostTrashAddService postTrashAddService;

//    /** ゴミ箱から戻す */
//    @Autowired
//    private CategoryTrashRemoveService categoryTrashRemoveService;
//
//    /** ゴミ箱一覧 */
//    @Autowired
//    private CategoryTrashListService categoryTrashListService;
//
//    /** ゴミ箱削除 */
//    @Autowired
//    private CategoryTrashDestroyService categoryTrashDestroyService;

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping({"", "{id}"})
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg, @PathVariable(name = "id", required = false) String id, ModelAndView mav) {

        // エラーメッセージ
        this.postControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        this.postControlDto.setPathNum(id);
        // ModelAndView
        this.postControlDto.setMav(mav);

        // 一覧表示処理
        this.postService.delegate(this.postControlDto);

        // ビューの設定
        mav.setViewName(POST_LIST);
        return mav;

    }


    /**
     * ゴミ箱へ移動
     */
    @PostMapping("trash-add")
    public ModelAndView trashAdd(@ModelAttribute("pagedListHolder") @Validated PostListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        postControlDto.setPostListForm(inputForm);
        // バリデーションエラー結果
        postControlDto.setBindingResult(result);
        // ModelAndView
        postControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        postControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        postTrashAddService.delegate(postControlDto);

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_POST_LIST);
        return mav;

    }
//
//    /**
//     * ゴミ箱から戻す
//     */
//    @PostMapping("trash-remove")
//    public ModelAndView trashRemove(@ModelAttribute("pagedListHolder") @Validated CategoryListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {
//
//        // ユーザー管理一覧フォーム
//        categoryControlDto.setCategoryListForm(inputForm);
//        // バリデーションエラー結果
//        categoryControlDto.setBindingResult(result);
//        // ModelAndView
//        categoryControlDto.setMav(mav);
//        // リダイレクト時に値を渡す
//        categoryControlDto.setRedirectAttributes(redirectAttributes);
//
//        // ゴミ箱へ移動する
//        categoryTrashRemoveService.delegate(categoryControlDto);
//
//        // エラーがあった場合
//        if (result.hasErrors()) {
//            // ビューの設定
//            mav.setViewName(REDIRECT_CATEGORY_MASTER_TRASH);
//            return mav;
//        }
//
//        // リダイレクト先
//        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER);
//        return mav;
//    }
//
//
//    /**
//     * ゴミ箱 一覧
//     */
//    @GetMapping({"trash-list", "trash-list/{id}"})
//    public ModelAndView deleteList(@ModelAttribute("errorMsg") String errorMsg, @PathVariable(name = "id", required = false) String id, ModelAndView mav) {
//
//        // エラーメッセージ
//        categoryControlDto.setErrorMsg(errorMsg);
//        // パスパラメータ
//        categoryControlDto.setPathNum(id);
//        // ModelAndView
//        categoryControlDto.setMav(mav);
//
//        // 一覧表示処理
//        categoryTrashListService.delegate(categoryControlDto);
//
//        // ビューの設定
//        mav.setViewName(CATEGORY_MASTER_TRASH);
//        return mav;
//
//    }
//
//    /**
//     * ゴミ箱のチェックしたデータを削除
//     */
//    @PostMapping("trash-destroy")
//    public ModelAndView trashDestroy(@ModelAttribute("pagedListHolder") @Validated CategoryListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {
//
//        // ユーザー管理一覧フォーム
//        categoryControlDto.setCategoryListForm(inputForm);
//        // バリデーションエラー結果
//        categoryControlDto.setBindingResult(result);
//        // ModelAndView
//        categoryControlDto.setMav(mav);
//        // リダイレクト時に値を渡す
//        categoryControlDto.setRedirectAttributes(redirectAttributes);
//
//        // ゴミ箱へ移動する
//        categoryTrashDestroyService.delegate(categoryControlDto);
//
//        // エラーがあった場合
//        if (result.hasErrors()) {
//            // ビューの設定
//            mav.setViewName(REDIRECT_CATEGORY_MASTER_TRASH);
//            return mav;
//        }
//
//        // リダイレクト先
//        mav = new ModelAndView(REDIRECT_CATEGORY_MASTER_TRASH);
//        return mav;
//    }


}
