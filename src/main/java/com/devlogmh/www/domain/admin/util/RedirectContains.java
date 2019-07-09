package com.devlogmh.www.domain.admin.util;

/**
 * リダイレクト先定数
 */
public class RedirectContains {

    //----------- プロフィール ----------------

    /** プロフィール */
    public static final String REDIRECT_PROFILE = "redirect:/admin/profile";

    //----------- ユーザー管理 ----------------

    /** ユーザー管理 */
    public static final String REDIRECT_USER_MASTER = "redirect:/admin/user-master";

    /** ユーザー管理 ゴミ箱 */
    public static final String REDIRECT_USER_MASTER_TRASH = "redirect:/admin/user-master/trash-list";


    //----------- カテゴリーマスタ ----------------

    /** カテゴリーマスタ */
    public static final String REDIRECT_CATEGORY_MASTER = "redirect:/admin/category-master";

    /** カテゴリーマスタ ゴミ箱 */
    public static final String REDIRECT_CATEGORY_MASTER_TRASH = "redirect:/admin/category-master/trash-list";


    //----------- 投稿記事管理 ----------------

    /** 一覧 */
    public static final String REDIRECT_POST_LIST = "redirect:/admin/post";

    /** ゴミ箱 */
    public static final String REDIRECT_POST_LIST_TRASH = "redirect:/admin/post/trash-list";

    //----------- 画像管理 ----------------

    /** 一覧 */
    public static final String REDIRECT_IMAGES_LIST = "redirect:/admin/images";

    /** 詳細 */
    public static final String REDIRECT_IMAGES_UPLOAD = "redirect:/admin/images/new";

}
