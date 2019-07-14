package com.devlogmh.www.domain.service.category;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.Pager;
import com.devlogmh.www.domain.model.PagerLink;
import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.model.blog.BlogDisplay;
import com.devlogmh.www.domain.model.blog.BlogMetaDisplay;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.domain.util.WebInfoUtil;
import com.devlogmh.www.mapper.BlogMapper;
import com.devlogmh.www.mapper.CategoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        List<CategoryDto> categoryList = WebInfoUtil.setupCategoryList(categoryMapper, request);
        this.mav.addObject("categoryList", categoryList);

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

        // サービスの初期処理
        PagedListHolder<BlogDisplay> pagedListHolder = this.getCategoryList(pathNum, categoryName);

        // WebサイトのMeta情報を取得
        BlogMetaDisplay blogMetaDisplay = this.setupBlogMetaDisplay(categoryName);

        // ページャーの設定
        Pager pager = this.setupPager(pagedListHolder);

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



    /**
     * ページャーを生成して取得
     */
    private Pager setupPager(PagedListHolder pagedListHolder) {

        Pager pager = new Pager();

        // 最初のページ
        pager.setFirstPage(pagedListHolder.isFirstPage());
        // 最後のページ
        pager.setLastPage(pagedListHolder.isLastPage());
        // ページャーのリンク数
        this.setPagerLinkList(pager, pagedListHolder);
        // 前のページ
        pager.setPrevPageLink(this.calcPrevPage(pagedListHolder.getPage()));
        // 後ろのページ
        pager.setNextPageLink(this.calcNextPage(pagedListHolder.getPage()));
        // 次ページが存在するか
        pager.setNextPage(pagedListHolder.getPageCount() != 1);

        return pager;

    }

    /**
     * ページャーの拡張設定
     * @param pager
     * @param pagedListHolder
     */
    private void setPagerLinkList(Pager pager, PagedListHolder pagedListHolder) {

        // リンクのある最後のページ - 最初のページ
        int pageDiff = pagedListHolder.getLastLinkedPage() - pagedListHolder.getFirstLinkedPage();

        // ページャーのリンクと表示リストの準備
        List<PagerLink> pagerLinkList = new ArrayList<>();

        // ページャーのリンク数を計算
        for (int i = 0; i <= pageDiff; i++) {
            // ページャーリスト用
            PagerLink pagerLink = new PagerLink();
            // ページャー リンク
            pagerLink.setLink(SiteInfoUtil.getRootPath(request) + String.valueOf(i));
            // ページャー 表示テキスト
            pagerLink.setText(String.valueOf(i + 1));
            // ページャー 現在位置判定
            pagerLink.setCurrentPage(this.isCurrentPage(i));
            // リストに追加
            pagerLinkList.add(pagerLink);

            this.isCurrentPage(i);
        }

        // ページャーリストを設定
        pager.setPagerLinkList(pagerLinkList);

    }

    /**
     * 現在ページの判定
     * @param currentPage
     * @return true 現在ページ / false 別ページ
     */
    private boolean isCurrentPage(int currentPage) {

        boolean result = false;

        if (StringUtils.equals(blogControlDto.getPathNum(), String.valueOf(currentPage))) {
            result = true;
        }

        return result;

    }

    /**
     * 前ページの計算
     */
    private int calcPrevPage(int page) {
        return page - 1;
    }

    /**
     * 後ろページの計算
     */
    private int calcNextPage(int page) {
        return page + 1;
    }


}
