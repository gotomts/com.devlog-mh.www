package com.devlogmh.www.domain.model.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class CategoryEntity {

    /**
     * カテゴリーID
     */
    private Long id;

    /**
     * カテゴリー名
     */
    private String categoryName;

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
    public CategoryEntity(String categoryName, Long updaterId,
                          Timestamp created, Timestamp updated, int delflg) {
        this.categoryName = categoryName;
        this.updaterId = updaterId;
        this.created = created;
        this.updated = updated;
        this.delflg = delflg;
    }

}
