package com.devlogmh.www.domain.util;

import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.model.Pager;
import com.devlogmh.www.domain.model.PagerLink;
import com.devlogmh.www.domain.model.util.BaseControlDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.support.PagedListHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * ページャー生成用 共通クラス
 */
public class PagerUtil {

    /**
     * ページャーを生成して取得
     */
    public static Pager setupPager(PagedListHolder pagedListHolder, BaseControlDto baseControlDto, HttpServletRequest request) {

        Pager pager = new Pager();

        // 最初のページ
        pager.setFirstPage(pagedListHolder.isFirstPage());
        // 最後のページ
        pager.setLastPage(pagedListHolder.isLastPage());
        // ページャーのリンク数
        setPagerLinkList(pager, pagedListHolder, baseControlDto, request);
        // 前のページ
        pager.setPrevPageLink(calcPrevPage(pagedListHolder.getPage(), baseControlDto, request));
        // 後ろのページ
        pager.setNextPageLink(calcNextPage(pagedListHolder.getPage(), baseControlDto, request));
        // 次ページが存在するか
        pager.setNextPage(pagedListHolder.getPageCount() != 1);

        return pager;

    }

    /**
     * カテゴリーパラメータ付きページャー
     * @param pagedListHolder
     * @param baseControlDto
     * @param request
     * @param categoryPath
     * @return pager
     */
    public static Pager setupPager(PagedListHolder pagedListHolder, BaseControlDto baseControlDto, HttpServletRequest request, String categoryPath) {

        // baseControlDtoにカテゴリーパラメータを設定
        baseControlDto.setPathCategory(categoryPath);

        // ページャーを設定
        Pager pager = setupPager(pagedListHolder,baseControlDto, request);

        return pager;
    }

    /**
     * ページャーの拡張設定
     * @param pager
     * @param pagedListHolder
     */
    private static void setPagerLinkList(Pager pager, PagedListHolder pagedListHolder, BaseControlDto baseControlDto, HttpServletRequest request) {

        // リンクのある最後のページ - 最初のページ
        int pageDiff = pagedListHolder.getLastLinkedPage() - pagedListHolder.getFirstLinkedPage();

        // ページャーのリンクと表示リストの準備
        List<PagerLink> pagerLinkList = new ArrayList<>();

        // ページャーのリンク数を計算
        for (int i = 0; i <= pageDiff; i++) {
            // ページャーリスト用
            PagerLink pagerLink = new PagerLink();
            // ページャー リンク
            pagerLink.setLink(SiteInfoUtil.getCurrentURL(request, baseControlDto.getPathNum()) + String.valueOf(i));
            // ページャー 表示テキスト
            pagerLink.setText(String.valueOf(i + 1));
            // ページャー 現在位置判定
            pagerLink.setCurrentPage(isCurrentPage(i, baseControlDto));
            // リストに追加
            pagerLinkList.add(pagerLink);

            isCurrentPage(i, baseControlDto);
        }

        // ページャーリストを設定
        pager.setPagerLinkList(pagerLinkList);

    }

    /**
     * 現在ページの判定
     * @param currentPage
     * @return true 現在ページ / false 別ページ
     */
    private static boolean isCurrentPage(int currentPage, BaseControlDto baseControlDto) {

        boolean result = false;

        if (StringUtils.equals(baseControlDto.getPathNum(), String.valueOf(currentPage))) {
            result = true;
        }

        return result;

    }

    /**
     * 前ページの計算
     * @param page
     * @param baseControlDto
     * @return
     */
    private static String calcPrevPage(int page, BaseControlDto baseControlDto, HttpServletRequest request) {
        page = page - 1;
        return urlConcatToPage(page, baseControlDto, request);
    }

    /**
     * 後ろページの計算
     * @param page
     * @param baseControlDto
     * @return
     */
    private static String calcNextPage(int page, BaseControlDto baseControlDto, HttpServletRequest request) {
        page = page + 1;
        return urlConcatToPage(page, baseControlDto, request);
    }

    /**
     * ページ計算後のURL連結
     * @param page
     * @return
     */
    private static String urlConcatToPage(int page, BaseControlDto baseControlDto, HttpServletRequest request) {

        String url;

        // カテゴリーパスが存在するか判定
        url = SiteInfoUtil.getCurrentURL(request, baseControlDto.getPathNum()) + String.valueOf(page);

        return url;
    }


}
