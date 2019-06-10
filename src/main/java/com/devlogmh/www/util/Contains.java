package com.devlogmh.www.util;

public class Contains {

    /**
     * 時間のフォーマット
     */
    public static final String TIME_FORMAT = "yyyy/MM/dd HH:mm";

    public static enum RoleList {

        /** 選択してください */
        UNSELECTED("", "選択してください"),

        /** 1.管理者 */
        ADMIN("1", "管理者"),

        /** 2.一般ユーザー */
        USER("2", "一般ユーザー");
        ;

        private final String id;
        private final String name;

        private RoleList(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

    }

    /**
     * 権限
     */
    public static enum RoleId {

        /** 1.選択してください */
        UNSELECTED(""),

        /** 2.管理者 */
        ADMIN("2"),

        /** 3.一般ユーザー */
        USER("3");

        /** フィールド定義 */
        private String id;

        /** コンストラクタ */
        private RoleId(String id) {
            this.id = id;
        }

        // 値を取得
        public String getValue() {
            return this.id;
        }

    }

    public static enum RoleName {
        /** 1.選択してください */
        UNSELECTED("選択してください"),

        /** 2.管理者 */
        ADMIN("管理者"),

        /** 3.一般ユーザー */
        USER("一般ユーザー");

        /** フィールド定義 */
        private String name;

        /** コンストラクタ */
        private RoleName(String name) {
            this.name = name;
        }

        // 値を取得
        public String getValue() {
            return this.name;
        }
    }

    /**
     * 削除フラグ
     */
    public static enum DelFlg {

        /**
         * 0.未削除
         */
        NotDel("0"),

        /**
         * 1.削除
         */
        Delete("1");

        /** フィールド定義 */
        private String id;

        /** コンストラクタ */
        private DelFlg(String id) {
            this.id = id;
        }

        // 値を取得
        public String getValue() {
            return this.id;
        }

    }

}
