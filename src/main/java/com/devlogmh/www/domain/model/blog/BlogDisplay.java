package com.devlogmh.www.domain.model.blog;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * ブログ記事表示ディスプレイクラス
 */
public class BlogDisplay {

    /** 投稿記事ID */
    @Getter
    @Setter
    private Long id;

    /** url */
    @Getter
    @Setter
    private String url;

    /** タイトル */
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

    /** カテゴリーID */
    @Setter
    @Getter
    private Long categoryId;

    /** カテゴリー名 */
    @Setter
    @Getter
    private String categoryName;

    /** ステータスID */
    @Setter
    @Getter
    private Long statusId;

    /** アイキャッチ画像／URL */
    @Getter
    @Setter
    private String topImageUrl;

    /** アイキャッチ画像／Titleタグ */
    @Getter
    @Setter
    private String topImageTitle;

    /** アイキャッチ画像／Altタグ */
    @Getter
    @Setter
    private String topImageAlt;

    /** コンテンツ */
    @Getter
    @Setter
    private String content;

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
     * フォーマット後の更新時間
     */
    @Getter
    @Setter
    private String updateTime;

    /**
     * 削除フラグ
     */
    @Getter
    @Setter
    private Integer delflg;


}
