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


    // ----------- ログイン ----------------

    /** ログイン */
    public static final String LOGIN = "app/admin/login/login";


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


}
