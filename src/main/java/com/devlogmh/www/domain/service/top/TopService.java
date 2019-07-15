package com.devlogmh.www.domain.service.top;

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
 * トップページ初期画面 サービス
 */
@Service
@Transactional
public class TopService extends AbsUtilService {

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

        // パスパラメータを取得
        String pathNum = blogControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }

        // WebサイトのMeta情報を取得
        BlogMetaDisplay blogMetaDisplay = this.setupBlogMetaDisplay();

        // サービスの初期処理
        PagedListHolder<BlogDisplay> pagedListHolder = this.init(pathNum);

        // ページャーの設定
        Pager pager = PagerUtil.setupPager(pagedListHolder, this.blogControlDto, this.request);

        // MAVにオブジェクトを詰める
        this.mav.addObject("blogMetaDisplay", blogMetaDisplay);
        this.mav.addObject("pagedListHolder", pagedListHolder);
        this.mav.addObject("pager", pager);

    }

    /**
     * Webサイト Meta情報設定
     */
    private BlogMetaDisplay setupBlogMetaDisplay() {

        BlogMetaDisplay blogMetaDisplay = new BlogMetaDisplay();

        // Title
        blogMetaDisplay.setTitle(WEB_SITE_TITLE);
        // Keyword
        blogMetaDisplay.setKeyword(WEB_SITE_KEYWORD);
        // Description
        blogMetaDisplay.setDescription(WEB_SITE_DESCRIPTION);

        return blogMetaDisplay;

    }

    /**
     * ブログ記事取得します。<br />
     * @return ブログ記事DTO
     */
    private PagedListHolder<BlogDisplay> init(String id) {

        // ブログ記事を取得
        List<BlogDisplay> displayList = blogMapper.selectBlogDisplayList();

        return this.createPageList(displayList, id);

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
            // URL
            dto.setUrl(WebInfoUtil.getSetupUrl(request, BLOG_URL, dto.getCategoryName() + "/", dto.getUrl()));
            // 作成日
            dto.setDate(TimestampUtil.formattedTimestamp(dto.getCreated(), TIME_FORMAT_DATE));
            // カテゴリーURL
            dto.setCategoryUrl(WebInfoUtil.getSetupUrl(request, CATEGORY_URL, dto.getCategoryName()));
            // コンテンツ
            dto.setContent(dto.getShortContent());
            // アイキャッチ画像の有無
            dto.setTopImage(dto.isTopImage(dto.getTopImageUrl()));

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
