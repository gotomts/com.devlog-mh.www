package com.devlogmh.www.domain.model.images;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 画像管理 コントローラーで設定したメソッドの引数をサービスへ橋渡しします。
 */
@Component
public class ImagesControlDto extends BaseControlDto {

    /**
     * パスパラメータ用カテゴリーID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * 画像管理フォーム
     */
    @Getter
    @Setter
    private ImagesListForm imagesListForm;

    /**
     * 画像管理フォーム
     */
    @Getter
    @Setter
    private ImagesForm imagesForm;

}
