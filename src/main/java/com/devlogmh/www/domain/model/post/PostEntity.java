package com.devlogmh.www.domain.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class PostEntity {

    /**
     * 投稿記事ID
     */
    private Long id;

    /**
     * url
     */
    private String url;

    /**
     * title
     */
    private String title;

    /**
     * description
     */
    private String description;

    /**
     * keyword
     */
    private String keyword;

    /**
     * content
     */
    private String content;

    /**
     * 画像
     */
    private Long topImageId;

    /**
     * カテゴリー
     */
    private Long categoryId;

    /**
     * 状況
     */
    private Long statusId;

    /**
     * 更新者
     */
    private Long updaterId;

    /**
     * 作成日時
     */
    private Timestamp created;

    /**
     * 更新日時
     */
    private Timestamp updated;

    /**
     * 削除フラグ
     */
    private Integer delflg;

    /**
     * コンストラクター
     * @param url
     * @param title
     * @param description
     * @param keyword
     * @param content
     * @param topImageId
     * @param categoryId
     * @param statusId
     * @param updaterId
     * @param created
     * @param updated
     * @param delflg
     */
    public PostEntity(String url, String title, String description, String keyword,
                      String content, Long topImageId, Long categoryId, Long statusId,
                      Long updaterId, Timestamp created, Timestamp updated, Integer delflg) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.keyword = keyword;
        this.content = content;
        this.topImageId = topImageId;
        this.categoryId = categoryId;
        this.statusId = statusId;
        this.updaterId = updaterId;
        this.created = created;
        this.updated = updated;
        this.delflg = delflg;
    }
}
