package com.devlogmh.www.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * ページャーリストクラス
 */
public class PagerLink {

    /** ページャーのリンク */
    @Getter
    @Setter
    private String link;

    /** ページャーのテキスト */
    @Getter
    @Setter
    private String text;

    /** ページャーの現在位置 */
    @Getter
    @Setter
    private boolean isCurrentPage;

}
