package com.devlogmh.www.domain.service.category;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.Pager;
import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.model.blog.BlogDisplay;
import com.devlogmh.www.domain.model.blog.BlogMetaDisplay;
import com.devlogmh.www.domain.util.PagerUtil;
import com.devlogmh.www.domain.util.WebInfoUtil;
import com.devlogmh.www.mapper.BlogMapper;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.devlogmh.www.domain.admin.util.Contains.PAGE_VIEW_SIZE;
import static com.devlogmh.www.domain.admin.util.Contains.TIME_FORMAT_DATE;
import static com.devlogmh.www.domain.contains.WebInfoContains.*;

/**
 * カテゴリー一覧 サービス
 */
@Service
@Transactional
public class CategoryListService extends AbsUtilService {

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

        // パスパラメータを取得
        String pathNum = blogControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            blogControlDto.setPathNum("0");
        }

        // グローバルナビゲーションに表示するカテゴリー一覧
        WebInfoUtil.setupCategoryList(categoryMapper, request, mav);

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // パスパラメータIDを取得
        String pathNum = this.blogControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }
        String categoryName = this.blogControlDto.getCategory();

        // カテゴリーで絞り込んだ記事一覧の取得
        PagedListHolder<BlogDisplay> pagedListHolder = this.getCategoryList(pathNum, categoryName);

        // WebサイトのMeta情報を取得
        BlogMetaDisplay blogMetaDisplay = this.setupBlogMetaDisplay(categoryName);

        // ページャーの設定
        Pager pager = PagerUtil.setupPager(pagedListHolder, this.blogControlDto, this.request, categoryName);

        // MAVにオブジェクトを詰める
        this.mav.addObject("blogMetaDisplay", blogMetaDisplay);
        this.mav.addObject("pagedListHolder", pagedListHolder);
        this.mav.addObject("pager", pager);

    }

    /**
     * ブログ記事取得します。<br />
     * @return ブログ記事DTO
     */
    private PagedListHolder<BlogDisplay> getCategoryList(String id, String categoryName) {

        // ブログ記事を取得
        List<BlogDisplay> displayList = this.blogMapper.selectBlogDisplayListByCategory(categoryName);

        return this.createPageList(displayList, id);

    }

    /**
     * Webサイト Meta情報設定
     */
    private BlogMetaDisplay setupBlogMetaDisplay(String categoryName) {

        BlogMetaDisplay blogMetaDisplay = new BlogMetaDisplay();

        // Title
        blogMetaDisplay.setTitle(categoryName);
        // Keyword
        blogMetaDisplay.setKeyword(WEB_SITE_KEYWORD);
        // Description
        blogMetaDisplay.setDescription(WEB_SITE_DESCRIPTION);

        return blogMetaDisplay;

    }

    /**
     * 記事リストをページャーが作れるリストに変換します。
     * @param displayList
     * @param id
     * @return pagenation
     */
    private PagedListHolder<BlogDisplay> createPageList(List<BlogDisplay> displayList, String id) {

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (BlogDisplay dto : displayList) {

            // DTOに情報を詰める
            // ID
            dto.setId(dto.getId());
            // タイトル
            dto.setTitle(dto.getTitle());
            // URL
            dto.setUrl(WebInfoUtil.getSetupUrl(request, BLOG_URL, dto.getCategoryName() + "/", dto.getUrl()));
            // 作成日
            dto.setDate(TimestampUtil.formattedTimestamp(dto.getCreated(), TIME_FORMAT_DATE));
            // カテゴリーURL
            dto.setCategoryUrl(WebInfoUtil.getSetupUrl(request, CATEGORY_URL, dto.getCategoryName()));
            // カテゴリー名
            dto.setCategoryName(dto.getCategoryName());
            // コンテンツ
            dto.setContent(dto.getContent());
            // アイキャッチ画像の有無
            dto.setTopImage(dto.isTopImage(dto.getTopImageUrl()));
            // アイキャッチ画像URL
            dto.setTopImageUrl(dto.getTopImageUrl());
            // アイキャッチ画像タイトル
            dto.setTopImageTitle(dto.getTopImageTitle());
            // アイキャッチ画像ALT
            dto.setTopImageAlt(dto.getTopImageAlt());

        }

        // DTOをページャー用に変換
        PagedListHolder<BlogDisplay> pagenation = new PagedListHolder<>(displayList);
        // 現在のページ位置を渡す
        pagenation.setPage(new Integer(id));
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
