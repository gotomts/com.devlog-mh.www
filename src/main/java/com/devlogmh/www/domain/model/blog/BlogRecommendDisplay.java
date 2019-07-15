package com.devlogmh.www.domain.model.blog;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

/**
 * ブログ おすすめ記事表示クラス
 */
public class BlogRecommendDisplay {

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
     * タイトル
     */
    @Getter
    @Setter
    private String title;

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
     * 作成日
     */
    @Getter
    @Setter
    private Timestamp created;

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
