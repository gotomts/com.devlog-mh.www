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

        /** 1.管理者 */
        Admin(1),

        /** 2.一般ユーザー */
        User(2);

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
