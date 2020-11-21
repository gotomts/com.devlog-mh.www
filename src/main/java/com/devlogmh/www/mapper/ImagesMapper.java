package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.images.ImagesDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * カテゴリーマスタのO/Rマッピング
 */
@Mapper
public interface ImagesMapper {

    /**
     * 新規登録
     */
    void insert(ImagesDto imagesDto);

    /**
     * 更新処理
     */
    void update(ImagesDto imagesDto);

    /**
     * IDをキーに1件検索
     * @param id
     * @return
     */
    ImagesDto select(Long id);

    /**
     * 全件検索
     * @return
     */
    List<ImagesDto> selectAll();

    /**
     * 削除フラグを条件に全件検索
     * @param delflg
     * @return
     */
    List<ImagesDto> selectImagesList(@Param("delflg") int delflg);

    /**
     * 1件の削除処理
     * @param imagesDto
     */
    void destroyOne(ImagesDto imagesDto);

}
