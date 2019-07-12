package com.devlogmh.www.domain.admin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Contains {

    /**
     * 1ページに表示するページ数を設定
     */
    public static final int PAGE_VIEW_SIZE = 10;

    /**
     * 時間のフォーマット
     */
    public static final String TIME_FORMAT = "yyyy/MM/dd HH:mm";

    /**
     * 画像アップロード名
     */
    public static final String UPLOAD_FILE_NAME = "post-top-image";

    /**
     * デフォルト URL
     */
    public static final String DEFAULT_URL = "1";

    /**
     * DelFlg
     */
    @AllArgsConstructor
    public static enum DelFlg {

        /** 未削除 */
        NOT_DEL(0),

        /** 削除 */
        DELETE(1);

        @Getter
        private final int value;

    }

    /**
     * 権限／リスト
     */
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
     * ステータス／リスト
     */
    public static enum StatusList {

        /** 選択してください */
        UNSELECTED("", "選択してください"),

        /** 1.下書き */
        DRAFT("1", "下書き"),

        /** 8.公開 */
        PUBLIC("8", "公開");

        private final String id;
        private final String name;

        private StatusList(String id, String name) {
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

}
