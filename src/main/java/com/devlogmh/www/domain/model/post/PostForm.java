package com.devlogmh.www.domain.model.post;

import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.mapper.CategoryMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.devlogmh.www.domain.admin.util.Contains.DelFlg.NOT_DEL;
import static com.devlogmh.www.domain.admin.util.Contains.StatusList.*;

/**
 * 投稿記事フォームクラス
 */
public class PostForm implements Serializable {

    /**
     *  投稿記事ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * url
     */
    @Getter
    @Setter
    @NotEmpty(message = "必須項目です。")
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String url;

    /**
     * タイトル
     */
    @Getter
    @Setter
    @NotEmpty(message = "必須項目です。")
    @Size(max = 64, message = "64文字以下で入力してください。")
    private String title;

    /**
     * キーワード
     */
    @Getter
    @Setter
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String keyword;

    /**
     * ディスクリプション
     */
    @Getter
    @Setter
    @Size(max = 255, message = "255文字以下で入力してください。")
    private String description;

    /**
     * カテゴリーID
     */
    @Setter
    @Getter
    @NotNull(message = "カテゴリーを選択してください。")
    private Long categoryId;

    /**
     * カテゴリー／リスト
     */
    @Setter
    @Getter
    private Map<String, String> categoryList;

    /**
     * ステータスID
     */
    @Setter
    @Getter
    @NotNull(message = "ステータスを選択してください。")
    private Long statusId;

    /**
     * ステータス／リスト
     */
    @Setter
    @Getter
    private Map<String, String> statusList;

    /**
     * アイキャッチ画像
     */
    @Getter
    @Setter
    private MultipartFile uploadFile;

    /**
     * アイキャッチ画像／URL
     */
    @Getter
    @Setter
    private String topImageUrl;

    /**
     * コンテンツ
     */
    @Getter
    @Setter
    private String content;

    /**
     * 更新者／ID
     */
    @Getter
    @Setter
    private Long updaterId;

    /**
     * 更新者／名称
     */
    @Getter
    @Setter
    private String updaterName;

    /**
     * 作成日時
     */
    @Getter
    @Setter
    private Timestamp created;

    /**
     * 更新日時
     */
    @Getter
    @Setter
    private Timestamp updated;

    /**
     * フォーマット後の更新時間
     */
    @Getter
    @Setter
    private String updateTime;

    /**
     * 削除フラグ
     */
    @Getter
    @Setter
    private Integer delflg;

    /**
     * ルートパス
     */
    @Getter
    @Setter
    private String rootPath;

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
