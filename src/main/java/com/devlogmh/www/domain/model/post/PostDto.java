package com.devlogmh.www.domain.model.post;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 投稿記事情報DTO
 */
public class PostDto extends PostEntity {

    /**
     * チェックボックス
     */
    @Getter
    @Setter
    private Long[] checkId;

    /**
     * カテゴリー名
     */
    @Getter
    @Setter
    private String categoryName;

    /**
     * ステータス名
     */
    @Getter
    @Setter
    private String statusName;

    /**
     * 更新者
     */
    @Getter
    @Setter
    private String updaterName;

    /**
     * フォーマット後の更新時間
     */
    @Setter
    @Getter
    private String updateTime;

    /**
     * エラーメッセージ
     */
    @Getter
    @Setter
    private String errorMsg;

    public String getUpdateTime(Timestamp updateTime) {
        return TimestampUtil.formattedTimestamp(updateTime, Contains.TIME_FORMAT);
    }

}
