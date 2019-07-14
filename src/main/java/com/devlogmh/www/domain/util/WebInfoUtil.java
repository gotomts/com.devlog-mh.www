package com.devlogmh.www.domain.util;

import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.mapper.CategoryMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.devlogmh.www.domain.admin.util.Contains.DelFlg.NOT_DEL;
import static com.devlogmh.www.domain.contains.WebInfoContains.CATEGORY_URL;

/**
 * Webサイト情報取得用クラス
 */
public class WebInfoUtil {

    /**
     * URLを生成して取得
     * @param orgUrl
     * @return url
     */
    public static String getSetupUrl(HttpServletRequest request, String path, String orgUrl) {

        // 変数を初期化
        String url = null;

        // URLを結合
        url = SiteInfoUtil.getRootPath(request) + path + orgUrl;

        // 返却
        return url;

    }

    /**
     * URLを生成して取得
     * @param orgUrl
     * @return url
     */
    public static String getSetupUrl(HttpServletRequest request, String path1, String path2, String orgUrl) {

        // 変数を初期化
        String url = null;

        // URLを結合
        url = SiteInfoUtil.getRootPath(request) + path1 + path2 + orgUrl;

        // 返却
        return url;

    }

    /**
     * カテゴリー 一覧設定
     * @return categorylist
     */
    public static List<CategoryDto> setupCategoryList(CategoryMapper categoryMapper, HttpServletRequest request) {

        // カテゴリー名称一覧を取得
        List<CategoryDto> categoryList = categoryMapper.selectCategoryListOrderByCategoryName(NOT_DEL.getValue());
        for (CategoryDto categoryDto: categoryList) {
            categoryDto.setCategoryUrl(WebInfoUtil.getSetupUrl(request, CATEGORY_URL, categoryDto.getCategoryName()));
        }
        return categoryList;

    }

}
