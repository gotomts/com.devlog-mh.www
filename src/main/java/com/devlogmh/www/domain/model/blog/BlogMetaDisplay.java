package com.devlogmh.www.domain.model.blog;

import lombok.Getter;
import lombok.Setter;

/**
 * ブログ Meta情報表示クラス
 */
public class BlogMetaDisplay {

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

}
