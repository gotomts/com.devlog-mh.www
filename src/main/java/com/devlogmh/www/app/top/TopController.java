package com.devlogmh.www.app.top;

import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.service.top.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.TOP;

//import com.devlogmh.www.domain.service.top.TopService;

/**
 * トップページ表示 コントローラー
 */
@Controller
public class TopController {

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private BlogControlDto blogControlDto;

    /**
     * ブログ記事 表示サービス
     */
    @Autowired
    private TopService topService;


    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping("")
    public ModelAndView index(ModelAndView mav) {

        // ModelAndView
        this.blogControlDto.setMav(mav);

        // 初期表示処理
        this.topService.delegate(blogControlDto);

        // ビューの設定
        mav.setViewName(TOP);
        return mav;

    }

}
