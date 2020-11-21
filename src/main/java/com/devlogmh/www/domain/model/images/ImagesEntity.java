package com.devlogmh.www.domain.model.images;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 画像管理エンティティクラス
 */
@NoArgsConstructor
@Data
public class ImagesEntity {

    /**
     * 画像ID
     */
    private Long id;

    /**
     * URL
     */
    private String url;

    /**
     * titleタグ
     */
    private String title;

    /**
     * altタグ
     */
    private String alt;

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
     * デフォルトコンストラクタ
     */
    public ImagesEntity(String url, String title, String alt, Long updaterId,
                        Timestamp created, Timestamp updated, int delflg) {
        this.url = url;
        this.title = title;
        this.alt = alt;
        this.updaterId = updaterId;
        this.created = created;
        this.updated = updated;
        this.delflg = delflg;
    }

}
