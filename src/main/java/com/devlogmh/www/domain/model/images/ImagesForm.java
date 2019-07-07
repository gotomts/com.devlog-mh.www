package com.devlogmh.www.domain.model.images;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 画像管理フォームクラス
 */
public class ImagesForm implements Serializable {

    /**
     *  画像ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * URL
     */
    @Getter
    @Setter
    @NotEmpty(message = "必須項目です。")
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String url;

    /**
     * title
     */
    @Getter
    @Setter
    @NotEmpty(message = "必須項目です。")
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String title;

    /**
     * alt
     */
    @Getter
    @Setter
    @NotEmpty(message = "必須項目です。")
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String alt;

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
