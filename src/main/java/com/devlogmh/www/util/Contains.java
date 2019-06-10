package com.devlogmh.www.util;

public class Contains {

    /**
     * 時間のフォーマット
     */
    public static final String TIME_FORMAT = "yyyy/MM/dd HH:mm";

    /**
     * 権限
     */
    public static enum RoleId {

        /** 1.選択してください */
        UNSELECTED(1),

        /** 2.管理者 */
        ADMIN(2),

        /** 3.一般ユーザー */
        USER(3);

        /** フィールド定義 */
        private int id;

        /** コンストラクタ */
        private RoleId(int id) {
            this.id = id;
        }

        // 値を取得
        public int getValue() {
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
        NotDel(0),

        /**
         * 1.削除
         */
        Delete(1);

        /** フィールド定義 */
        private int id;

        /** コンストラクタ */
        private DelFlg(int id) {
            this.id = id;
        }

        // 値を取得
        public int getValue() {
            return this.id;
        }

    }

}
