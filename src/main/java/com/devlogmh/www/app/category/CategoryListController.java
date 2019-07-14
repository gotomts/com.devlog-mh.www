package com.devlogmh.www.app.category;

import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.service.category.CategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.CATEGORY_LIST;

/**
 * カテゴリー一覧表示 コントローラー
 */
@Controller
@RequestMapping("category")
public class CategoryListController {

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private BlogControlDto blogControlDto;

    /**
     * ブログ記事 表示サービス
     */
    @Autowired
    private CategoryListService categoryListService;


    /**
     * 初期表示
     * @param mav
     * @return
     */
    @GetMapping({"{category}","{category}/{id}"})
    public ModelAndView index(@PathVariable(name = "category", required = false) String categoryName,
                              @PathVariable(name = "id", required = false) String id,
                              ModelAndView mav) {

        // PathVariable カテゴリー名
        this.blogControlDto.setCategory(categoryName);
        // PathVariable ID
        this.blogControlDto.setPathNum(id);
        // ModelAndView
        this.blogControlDto.setMav(mav);

        // 初期表示処理
        this.categoryListService.delegate(blogControlDto);

        // ビューの設定
        mav.setViewName(CATEGORY_LIST);
        return mav;

    }

}
