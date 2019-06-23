package com.devlogmh.www.domain.model.category;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ユーザー管理のコントローラーで設定したメソッドの引数をサービスへ橋渡しします。
 */
@Data
@Component
public class CategoryControlDto extends BaseControlDto {

    /**
     * ユーザーID
     */
    private Long userId;

    /**
     * ユーザー管理一覧フォーム
     */
    private CategoryListForm usersListForm;

    /**
     * ユーザー管理フォーム
     */
    private CategoryForm usersForm;

}
