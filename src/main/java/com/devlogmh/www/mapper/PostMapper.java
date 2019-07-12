package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.blog.BlogDisplay;
import com.devlogmh.www.domain.model.post.PostDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 投稿記事のO/Rマッピング
 */
@Mapper
public interface PostMapper {

    /**
     * 新規登録
     */
    void insert(PostDto postDto);

    /**
     * 更新処理
     */
    void update(PostDto postDto);

    /**
     * IDをキーに1件検索
     * @param id
     * @return
     */
    PostDto select(Long id);

    /**
     * 全件検索
     * @return
     */
    List<PostDto> selectAll();

    /**
     * 削除フラグを条件に全件検索
     * @param delflg
     * @return
     */
    List<PostDto> selectPostList(@Param("delflg") int delflg);

    /**
     * ゴミ箱へ移動
     * @param postDto
     */
    void trashMove(PostDto postDto);

    /**
     * 完全削除
     * @param postDto
     */
    void destroy(PostDto postDto);

    /**
     * IDを全件検索
     * @return
     */
    List<Long> selectAllId();

    /**
     * ステータスが公開かつ削除フラグが0の記事を検索
     */
    List<BlogDisplay> selectBlogDisplayList();

}
