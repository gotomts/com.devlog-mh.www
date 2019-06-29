package com.devlogmh.www.domain.model.post;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * カテゴリーマスタのコントローラーで設定したメソッドの引数をサービスへ橋渡しします。
 */
@Component
public class PostControlDto extends BaseControlDto {

    /**
     * パスパラメータ用投稿記事ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * 投稿記事 一覧フォーム
     */
    @Getter
    @Setter
    private PostListForm postListForm;

    /**
     * 投稿記事 フォーム
     */
//    @Getter
//    @Setter
//    private CategoryForm categoryForm;

}
