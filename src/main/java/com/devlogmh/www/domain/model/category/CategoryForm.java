package com.devlogmh.www.domain.model.category;

import com.devlogmh.www.domain.admin.util.Contains;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * カテゴリーマスタフォームクラス
 */
public class CategoryForm implements Serializable {

    /**
     *  カテゴリーID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * カテゴリー名
     */
    @Getter
    @Setter
    @NotEmpty(message = "必須項目です。")
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String categoryName;

    /**
     * 更新者／ID
     */
    @Getter
    @Setter
    private Long updaterId;

    /**
     * 更新者／名称
     */
    @Getter
    @Setter
    private String updaterName;

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
