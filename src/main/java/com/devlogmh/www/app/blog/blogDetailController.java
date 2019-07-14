package com.devlogmh.www.app.blog;

import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.service.blog.BlogDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.BLOG_DETAIL;

/**
 * 投稿記事詳細 コントローラー
 */
@Controller
@RequestMapping("blog")
public class blogDetailController {

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private BlogControlDto blogControlDto;

    /**
     * ブログ記事 表示サービス
     */
    @Autowired
    private BlogDetailService blogDetailService;


    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("{category}/{url}")
    public ModelAndView index(@PathVariable("category") String category, @PathVariable("url") String url, ModelAndView mav) {

        // カテゴリー
        this.blogControlDto.setCategory(category);
        // URL
        this.blogControlDto.setUrl(url);
        // ModelAndView
        this.blogControlDto.setMav(mav);

        // 初期表示処理
        this.blogDetailService.delegate(blogControlDto);

        // ビューの設定
        mav.setViewName(BLOG_DETAIL);
        return mav;

    }

}
