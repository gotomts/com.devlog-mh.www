package com.devlogmh.www.domain.model.images;

import com.devlogmh.www.domain.model.category.CategoryForm;
import com.devlogmh.www.domain.model.category.CategoryListForm;
import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * カテゴリーマスタのコントローラーで設定したメソッドの引数をサービスへ橋渡しします。
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
     * カテゴリーマスタ一覧フォーム
     */
    @Getter
    @Setter
    private CategoryListForm categoryListForm;

    /**
     * カテゴリーマスタフォーム
     */
    @Getter
    @Setter
    private CategoryForm categoryForm;

}
