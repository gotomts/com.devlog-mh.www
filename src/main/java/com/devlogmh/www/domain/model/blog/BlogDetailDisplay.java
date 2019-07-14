package com.devlogmh.www.domain.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

/**
 * ブログ記事詳細 クラス
 */
public class BlogDetailDisplay {

    /**
     * 投稿記事ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * url
     */
    @Getter
    @Setter
    private String url;

    /**
     * カテゴリーURL
     */
    @Getter
    @Setter
    private String categoryUrl;

    /**
     * タイトル
     */
    @Getter
    @Setter
    private String title;

    /** キーワード */
    @Getter
    @Setter
    private String keyword;

    /** ディスクリプション */
    @Getter
    @Setter
    private String description;

    /**
     * カテゴリーID
     */
    @Setter
    @Getter
    private Long categoryId;

    /**
     * カテゴリー名
     */
    @Getter
    @Setter
    private String categoryName;

    /**
     * ステータスID
     */
    @Setter
    @Getter
    private Long statusId;

    /**
     * 画像 有無
     */
    @Setter
    @Getter
    private boolean isTopImage;

    /**
     * アイキャッチ画像／URL
     */
    @Getter
    @Setter
    private String topImageUrl;

    /**
     * アイキャッチ画像／Titleタグ
     */
    @Getter
    @Setter
    private String topImageTitle;

    /**
     * アイキャッチ画像／Altタグ
     */
    @Getter
    @Setter
    private String topImageAlt;

    /**
     * コンテンツ
     */
    @Getter
    @Setter
    private String content;

    /**
     * 公開日
     */
    @Getter
    @Setter
    private String date;

    /**
     * 作成日時
     */
    @Getter
    @Setter
    private Timestamp created;

    /**
     * 更新日時
     */
    @Getter
    @Setter
    private Timestamp updated;

    /**
     * 削除フラグ
     */
    @Getter
    @Setter
    private Integer delflg;

    /**
     * アイキャッチ画像の有無を判定し、取得します。
     *
     * @param topImageUrl
     * @return true 画像あり / false 画像なし
     */
    public boolean isTopImage(String topImageUrl) {

        // 画像の有無を判定
        this.isTopImage = StringUtils.isNotEmpty(topImageUrl);

        return this.isTopImage;
    }

}
