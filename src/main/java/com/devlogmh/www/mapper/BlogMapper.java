package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.blog.BlogDetailDisplay;
import com.devlogmh.www.domain.model.blog.BlogDisplay;
import com.devlogmh.www.domain.model.blog.BlogRecommendDisplay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 投稿記事の表画面 O/Rマッピング
 */
@Mapper
public interface BlogMapper {

    /**
     * ステータスが公開かつ削除フラグが0の記事を検索
     */
    List<BlogDisplay> selectBlogDisplayList();

    /**
     * ステータスが公開かつ削除フラグが0の記事を検索
     */
    List<BlogDisplay> selectBlogDisplayListByCategory(String category);

    /**
     * ブログ記事詳細
     */
    BlogDetailDisplay selectByCategoryAndUrl(@Param("category") String category, @Param("url") String url);

    /**
     * ブログおすすめ記事
     */
    List<BlogRecommendDisplay> selectBlogRecommendDisplayList(String category);
}
