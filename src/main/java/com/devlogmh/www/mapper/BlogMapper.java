package com.devlogmh.www.mapper;

import com.devlogmh.www.domain.model.blog.BlogDisplay;
import org.apache.ibatis.annotations.Mapper;

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

}
