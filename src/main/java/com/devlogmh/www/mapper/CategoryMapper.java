package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * カテゴリーマスタのO/Rマッピング
 */
@Mapper
public interface CategoryMapper {

    /**
     * 新規登録
     */
    void insert(CategoryDto categoryDto);

    /**
     * 更新処理
     */
    void update(CategoryDto categoryDto);

    /**
     * IDをキーに1件検索
     * @param id
     * @return
     */
    CategoryDto select(Long id);

    /**
     * 全件検索
     * @return
     */
    List<CategoryDto> selectAll();

    /**
     * 削除フラグを条件に全件検索
     * @param delflg
     * @return
     */
    List<CategoryDto> selectCategoryList(@Param("delflg") int delflg);

    /**
     * 削除フラグを条件に全件検索
     * @param delflg
     * @return
     */
    List<CategoryDto> selectCategoryListOrderByCategoryName(@Param("delflg") int delflg);

    /**
     * ゴミ箱へ移動
     * @param categoryDto
     */
    void trashMove(CategoryDto categoryDto);

    /**
     * 完全削除
     * @param categoryDto
     */
    void destroy(CategoryDto categoryDto);

}
