package com.devlogmh.www.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * ページャークラス
 */
public class Pager {

    /** 最初のページ */
    @Getter
    @Setter
    private boolean firstPage;

    /** 最後のページ */
    @Getter
    @Setter
    private boolean lastPage;

    /** ページ数 */
    @Getter
    @Setter
    private List<PagerLink> pagerLinkList;

    /** 現在のページの周囲にリンクを作成する最後のページを返します。 */
    @Getter
    @Setter
    private int firstLinkedPage;

    /** 現在のページの周囲にリンクを作成する最後のページを返します。 */
    @Getter
    @Setter
    private int lastLinkedPage;

    /** 前のページリンク */
    @Getter
    @Setter
    private String prevPageLink;

    /** 後ろのページリンク */
    @Getter
    @Setter
    private String nextPageLink;

    /** 次ページが存在するか */
    @Getter
    @Setter
    private boolean isNextPage;

}
