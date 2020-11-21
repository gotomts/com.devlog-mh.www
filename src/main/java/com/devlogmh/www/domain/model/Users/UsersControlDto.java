package com.devlogmh.www.domain.model.users;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ユーザー管理のコントローラーで設定したメソッドの引数をサービスへ橋渡しします。
 */
@Data
@Component
public class UsersControlDto extends BaseControlDto {

    /**
     * ユーザーID
     */
    private Long userId;

    /**
     * ユーザー管理一覧フォーム
     */
    private UsersListForm usersListForm;

    /**
     * ユーザー管理フォーム
     */
    private UsersForm usersForm;

}
