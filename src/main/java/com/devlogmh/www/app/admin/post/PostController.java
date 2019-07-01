package com.devlogmh.www.app.admin.post;

import com.devlogmh.www.domain.admin.service.post.*;
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
import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_POST_LIST_TRASH;
import static com.devlogmh.www.domain.admin.util.RoutesContains.POST_LIST;
import static com.devlogmh.www.domain.admin.util.RoutesContains.POST_LIST_TRASH;

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

    /** ゴミ箱から戻す */
    @Autowired
    private PostTrashRemoveService postTrashRemoveService;

    /** ゴミ箱一覧 */
    @Autowired
    private PostTrashListService postTrashListService;

    /** ゴミ箱削除 */
    @Autowired
    private PostTrashDestroyService postTrashDestroyService;

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

    /**
     * ゴミ箱から戻す
     */
    @PostMapping("trash-remove")
    public ModelAndView trashRemove(@ModelAttribute("pagedListHolder") @Validated PostListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        postControlDto.setPostListForm(inputForm);
        // バリデーションエラー結果
        postControlDto.setBindingResult(result);
        // ModelAndView
        postControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        postControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        postTrashRemoveService.delegate(postControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_POST_LIST_TRASH);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_POST_LIST_TRASH);
        return mav;
    }


    /**
     * ゴミ箱 一覧
     */
    @GetMapping({"trash-list", "trash-list/{id}"})
    public ModelAndView deleteList(@ModelAttribute("errorMsg") String errorMsg, @PathVariable(name = "id", required = false) String id, ModelAndView mav) {

        // エラーメッセージ
        postControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        postControlDto.setPathNum(id);
        // ModelAndView
        postControlDto.setMav(mav);

        // 一覧表示処理
        postTrashListService.delegate(postControlDto);

        // ビューの設定
        mav.setViewName(POST_LIST_TRASH);
        return mav;

    }

    /**
     * ゴミ箱のチェックしたデータを削除
     */
    @PostMapping("trash-destroy")
    public ModelAndView trashDestroy(@ModelAttribute("pagedListHolder") @Validated PostListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // ユーザー管理一覧フォーム
        postControlDto.setPostListForm(inputForm);
        // バリデーションエラー結果
        postControlDto.setBindingResult(result);
        // ModelAndView
        postControlDto.setMav(mav);
        // リダイレクト時に値を渡す
        postControlDto.setRedirectAttributes(redirectAttributes);

        // ゴミ箱へ移動する
        postTrashDestroyService.delegate(postControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(REDIRECT_POST_LIST_TRASH);
            return mav;
        }

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_POST_LIST_TRASH);
        return mav;
    }


}
