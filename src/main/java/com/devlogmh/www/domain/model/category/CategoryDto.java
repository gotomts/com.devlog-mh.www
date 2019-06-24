package com.devlogmh.www.domain.model.category;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * カテゴエリー情報DTO
 */
public class CategoryDto extends CategoryEntity {

    /**
     * チェックボックス
     */
    @Getter
    @Setter
    private Long[] checkId;

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
