package com.devlogmh.www.domain.service.blog;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.model.blog.BlogDetailDisplay;
import com.devlogmh.www.domain.model.blog.BlogMetaDisplay;
import com.devlogmh.www.domain.util.WebInfoUtil;
import com.devlogmh.www.mapper.BlogMapper;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
        WebInfoUtil.setupCategoryList(categoryMapper, request, mav);

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

        // ブログ記事詳細 拡張設定
        this.setupBlogDetailDisplay(blogDetailDisplay);

        // WebサイトのMeta情報を取得
        BlogMetaDisplay blogMetaDisplay = this.setupBlogMetaDisplay(blogDetailDisplay);

        // MAVにオブジェクトを詰める
        this.mav.addObject("blogDetailDisplay", blogDetailDisplay);
        this.mav.addObject("blogMetaDisplay", blogMetaDisplay);

    }

    /**
     * ブログ記事詳細 拡張設定
     * @param blogDetailDisplay
     */
    private void setupBlogDetailDisplay(BlogDetailDisplay blogDetailDisplay) {

        // カテゴリーURL
        blogDetailDisplay.setCategoryUrl(WebInfoUtil.getSetupUrl(request, CATEGORY_URL, blogDetailDisplay.getCategoryName()));

    }

    /**
     * Webサイト Meta情報設定
     * @param blogDetailDisplay
     * @return blogMetaDisplay
     */
    private BlogMetaDisplay setupBlogMetaDisplay(BlogDetailDisplay blogDetailDisplay) {

        // 設定の準備
        BlogMetaDisplay blogMetaDisplay = new BlogMetaDisplay();

        // Title
        blogMetaDisplay.setTitle(blogDetailDisplay.getTitle());
        // Keyword
        blogMetaDisplay.setKeyword(blogDetailDisplay.getKeyword());
        // Description
        blogMetaDisplay.setDescription(blogDetailDisplay.getDescription());

        return blogMetaDisplay;

    }

}
