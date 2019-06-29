package com.devlogmh.www.app.admin.post;

import com.devlogmh.www.domain.admin.service.post.PostService;
import com.devlogmh.www.domain.model.post.PostControlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
