package com.devlogmh.www.domain.model.images;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 画像管理DTO
 */
public class ImagesDto extends ImagesEntity {

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

    /**
     * 更新時間を取得
     * @param updateTime
     * @return フォーマット後の更新時間
     */
    public String getUpdateTime(Timestamp updateTime) {
        return TimestampUtil.formattedTimestamp(updateTime, Contains.TIME_FORMAT);
    }

}
