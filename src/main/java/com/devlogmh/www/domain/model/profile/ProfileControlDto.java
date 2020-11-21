package com.devlogmh.www.domain.model.profile;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * プロフィールのコントローラーで設定したメソッドの引数をサービスへ橋渡しします。
 */
@Data
@Component
public class ProfileControlDto extends BaseControlDto {

    /**
     * ユーザーID
     */
    private Long userId;

    /**
     * プロフィール フォーム
     */
    private ProfileForm profileForm;

}
