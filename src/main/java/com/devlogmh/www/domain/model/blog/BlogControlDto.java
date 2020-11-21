package com.devlogmh.www.domain.model.blog;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * ブログ記事 コントローラーで設定したメソッドの引数をサービスへ橋渡しします。
 */
@Component
public class BlogControlDto extends BaseControlDto {

    /**
     * パスパラメータ用ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * PathVariable用 カテゴリー
     */
    @Getter
    @Setter
    private String category;

    /**
     * PathVariable用 URL
     */
    @Getter
    @Setter
    private String url;

    /**
     * ブログ記事表示ディスプレイ
     */
    @Getter
    @Setter
    private BlogDisplay blogDisplay;

}
