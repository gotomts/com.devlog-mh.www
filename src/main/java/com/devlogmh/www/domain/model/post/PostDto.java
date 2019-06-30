package com.devlogmh.www.domain.model.post;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.mapper.CategoryMapper;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.devlogmh.www.domain.admin.util.Contains.DelFlg.NOT_DEL;
import static com.devlogmh.www.domain.admin.util.Contains.StatusList.*;

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
     * カテゴリー／リスト
     */
    @Setter
    @Getter
    private Map<String, String> categoryList;

    /**
     * ステータス／リスト
     */
    @Setter
    @Getter
    private Map<String, String> statusList;

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
     * 更新時間
     * @param updateTime
     * @return
     */
    public String getUpdateTime(Timestamp updateTime) {
        return TimestampUtil.formattedTimestamp(updateTime, Contains.TIME_FORMAT);
    }

    /**
     * ステータス／リスト getter
     * @return
     */
    public Map<String, String> getSelectedStatuses() {
        Map<String, String> selectMap = new LinkedHashMap<String, String>();
        selectMap.put(UNSELECTED.getId(), UNSELECTED.getName());
        selectMap.put(DRAFT.getId(), DRAFT.getName());
        selectMap.put(PUBLIC.getId(), PUBLIC.getName());
        return selectMap;
    }

    /**
     * カテゴリー／リスト gettter
     * @param categoryMapper
     * @return
     */
    public Map<String, String> getCategoryList(CategoryMapper categoryMapper) {

        // カテゴリー一覧を取得
        List<CategoryDto> categoryDtoList = categoryMapper.selectCategoryListOrderByCategoryName(NOT_DEL.getValue());

        Map<String, String> selectedCategoryList = new LinkedHashMap<>();

        // カテゴリーをプルダウン用のリストに詰める
        for (CategoryDto categoryDto : categoryDtoList) {
            selectedCategoryList.put(categoryDto.getId().toString(), categoryDto.getCategoryName());
        }

        return selectedCategoryList;

    }

}
