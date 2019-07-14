package com.devlogmh.www.domain.admin.util;

/**
 * ルーター定数
 */
public class RoutesContains {

    // ----------- エラーページ ----------------

    /** 404エラー */
    public static final String ERROR_404 = "app/admin/error/404";

    /** 4xxエラー */
    public static final String ERROR_4XX = "app/admin/error/4xx";

    /** 5xxエラー */
    public static final String ERROR_5XX = "app/admin/error/4xx";

    /** 上記以外のエラー */
    public static final String ERROR = "app/admin/error/error";

    // ------------------------------------
    // ▼ 表画面
    // ------------------------------------

    /** トップページ */
    public static final String TOP = "app/top/top";

    /** ブログ詳細 */
    public static final String BLOG_DETAIL = "app/blog/blog-detail";

    // ------------------------------------
    // ▼ 管理画面
    // ------------------------------------

    // ----------- ログイン ----------------

    /** ログイン */
    public static final String LOGIN = "app/admin/login/login";

    /** 管理画面トップ */
    public static final String ADMIN_INDEX = "app/admin/index/index";


    // ----------- プロフィール管理 ----------------

    /** プロフィール管理 */
    public static final String PROFILE_EDIT = "app/admin/profile/profile-edit";


    //----------- ユーザー管理 一覧 ----------------

    /** ユーザー管理 一覧 */
    public static final String USER_MASTER = "app/admin/user/user-master";

    /** ユーザー管理 ゴミ箱 一覧 */
    public static final String USER_MASTER_TRASH = "app/admin/user/user-master-trash-list";

    //----------- ユーザー管理 詳細 ----------------

    /** ユーザー管理 新規登録画面 */
    public static final String USER_MASTER_NEW = "app/admin/user/user-master-new";

    /** ユーザー管理 編集画面 */
    public static final String USER_MASTER_EDIT = "app/admin/user/user-master-edit";


    //----------- 投稿記事 一覧 ----------------

    /** 投稿記事 一覧 */
    public static final String POST_LIST = "app/admin/post/post-list";

    /** 投稿記事 ゴミ箱 */
    public static final String POST_LIST_TRASH = "app/admin/post/post-trash-list";


    //----------- 投稿記事 詳細 ----------------

    /** カテゴリー 新規登録画面 */
    public static final String POST_NEW = "app/admin/post/post-new";

    /** カテゴリー 編集画面 */
    public static final String POST_EDIT = "app/admin/post/post-edit";


    //----------- カテゴリー 一覧 ----------------

    /** カテゴリー管理 一覧 */
    public static final String CATEGORY_MASTER = "app/admin/category/category-master";

    /** カテゴリー管理 ゴミ箱 一覧 */
    public static final String CATEGORY_MASTER_TRASH = "app/admin/category/category-master-trash-list";

    //----------- カテゴリー 詳細 ----------------

    /** カテゴリー 新規登録画面 */
    public static final String CATEGORY_MASTER_NEW = "app/admin/category/category-master-new";

    /** カテゴリー 編集画面 */
    public static final String CATEGORY_MASTER_EDIT = "app/admin/category/category-master-edit";

    //----------- 画像管理 ----------------

    /** 画像管理 一覧 */
    public static final String IMAGES_LIST = "app/admin/images/images-list";

    /** 画像管理 新規登録画面 */
    public static final String IMAGES_NEW = "app/admin/images/images-new";

    /** 画像管理 編集画面 */
    public static final String IMAGES_EDIT = "app/admin/images/images-edit";


}
