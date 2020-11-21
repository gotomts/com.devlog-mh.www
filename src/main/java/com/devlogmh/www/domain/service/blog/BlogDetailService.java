package com.devlogmh.www.domain.service.blog;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.model.blog.BlogDetailDisplay;
import com.devlogmh.www.domain.model.blog.BlogMetaDisplay;
import com.devlogmh.www.domain.model.blog.BlogRecommendDisplay;
import com.devlogmh.www.domain.util.WebInfoUtil;
import com.devlogmh.www.mapper.BlogMapper;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.devlogmh.www.domain.admin.util.Contains.TIME_FORMAT_DATE;
import static com.devlogmh.www.domain.contains.WebInfoContains.BLOG_URL;
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

        // おすすめ記事取得（カテゴリー絞り込み）
        List<BlogRecommendDisplay> blogRecommendDisplayList = this.setupBlogRecommendDisplay(category);

        // MAVにオブジェクトを詰める
        this.mav.addObject("blogDetailDisplay", blogDetailDisplay);
        this.mav.addObject("blogMetaDisplay", blogMetaDisplay);
        this.mav.addObject("blogRecommendDisplayList", blogRecommendDisplayList);

    }

    /**
     * ブログ記事詳細 拡張設定
     * @param blogDetailDisplay
     */
    private void setupBlogDetailDisplay(BlogDetailDisplay blogDetailDisplay) {

        // 作成日
        blogDetailDisplay.setDate(TimestampUtil.formattedTimestamp(blogDetailDisplay.getCreated(), TIME_FORMAT_DATE));
        // カテゴリーURL
        blogDetailDisplay.setCategoryUrl(WebInfoUtil.getSetupUrl(request, CATEGORY_URL, blogDetailDisplay.getCategoryName()));
        // アイキャッチ画像の有無
        blogDetailDisplay.setTopImage(blogDetailDisplay.isTopImage(blogDetailDisplay.getTopImageUrl()));

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

    /**
     * おすすめ記事
     * @param category
     * @return blogRecommendDisplayList
     */
    private List<BlogRecommendDisplay> setupBlogRecommendDisplay(String category) {

        // おすすめ記事一覧を取得
        List<BlogRecommendDisplay> blogRecommendDisplayList = this.blogMapper.selectBlogRecommendDisplayList(category);

        for (BlogRecommendDisplay blogRecommendDisplay : blogRecommendDisplayList) {
            // URL
            blogRecommendDisplay.setUrl(WebInfoUtil.getSetupUrl(request, BLOG_URL, category + "/", blogRecommendDisplay.getUrl()));
            // 作成日
            blogRecommendDisplay.setDate(TimestampUtil.formattedTimestamp(blogRecommendDisplay.getCreated(), TIME_FORMAT_DATE));
            // アイキャッチ画像の有無
            blogRecommendDisplay.setTopImage(blogRecommendDisplay.isTopImage(blogRecommendDisplay.getTopImageUrl()));
            // コンテンツ
            blogRecommendDisplay.setContent(this.getRecommendContent(blogRecommendDisplay.getShortContent()));
            // アイキャッチ画像がなかった場合
            if (!blogRecommendDisplay.isTopImage()) {
                blogRecommendDisplay.setTopImageUrl("http://placehold.it/320x160/?text=Not Image");
                blogRecommendDisplay.setTopImageTitle("Not Image");
                blogRecommendDisplay.setTopImageAlt("Not Image");
            }
        }

        return blogRecommendDisplayList;

    }

    /**
     * おすすめ記事用にコメント整形
     * @param shortContent
     * @return content
     */
    private String getRecommendContent(String shortContent) {

        String content = null;

        if (Objects.nonNull(shortContent)) {
            if (shortContent.length() >= 30) {
                content = shortContent.substring(0, 29);
            } else {
                content = shortContent;
            }
        }

        return content;

    }

}
