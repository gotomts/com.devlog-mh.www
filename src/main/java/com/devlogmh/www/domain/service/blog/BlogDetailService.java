package com.devlogmh.www.domain.service.blog;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.model.blog.BlogDetailDisplay;
import com.devlogmh.www.domain.model.blog.BlogMetaDisplay;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.mapper.BlogMapper;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.devlogmh.www.domain.admin.util.Contains.DelFlg.NOT_DEL;
import static com.devlogmh.www.domain.contains.WebInfoContains.CATEGORY_URL;

/**
 * 記事詳細表示 サービス
 */
@Service
@Transactional
public class BlogDetailService extends AbsUtilService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BlogControlDto blogControlDto;

    @Autowired
    private HttpServletRequest request;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = this.blogControlDto.getMav();

        // グローバルナビゲーションに表示するカテゴリー一覧
        List<CategoryDto> categoryList = this.setupCategoryList();
        this.mav.addObject("categoryList", categoryList);

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // カテゴリーを取得
        String category = blogControlDto.getCategory();

        // URLを取得
        String url = blogControlDto.getUrl();

        // ブログ記事詳細
        BlogDetailDisplay blogDetailDisplay = blogMapper.selectByCategoryAndUrl(category, url);

        // WebサイトのMeta情報を取得
        BlogMetaDisplay blogMetaDisplay = this.setupBlogMetaDisplay(blogDetailDisplay);

        // MAVにオブジェクトを詰める
        this.mav.addObject("blogDetailDisplay", blogDetailDisplay);
        this.mav.addObject("blogMetaDisplay", blogMetaDisplay);

    }

    /**
     * Webサイト Meta情報設定
     */
    private BlogMetaDisplay setupBlogMetaDisplay(BlogDetailDisplay blogDetailDisplay) {

        BlogMetaDisplay blogMetaDisplay = new BlogMetaDisplay();

        // Title
        blogMetaDisplay.setTitle(blogDetailDisplay.getTitle());
        // Keyword
        blogMetaDisplay.setKeyword(blogDetailDisplay.getKeyword());
        // Description
        blogMetaDisplay.setDescription(blogDetailDisplay.getDescription());

        return blogMetaDisplay;

    }

    /**
     * カテゴリー 一覧設定
     * @return categorylist
     */
    private List<CategoryDto> setupCategoryList() {

        // カテゴリー名称一覧を取得
        List<CategoryDto> categoryList = categoryMapper.selectCategoryListOrderByCategoryName(NOT_DEL.getValue());
        for (CategoryDto categoryDto: categoryList) {
            categoryDto.setCategoryUrl(this.getSetupUrl(CATEGORY_URL, categoryDto.getCategoryName()));
        }
        return categoryList;

    }

    /**
     * URLを生成して取得
     * @param orgUrl
     * @return
     */
    private String getSetupUrl(String path, String orgUrl) {

        // 変数を初期化
        String url = null;

        // URLを結合
        url = SiteInfoUtil.getRootPath(request) + path + orgUrl;

        // 返却
        return url;

    }

}
