package com.devlogmh.www.domain.model.util;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * コントローラーで設定したメソッドの引数をサービスへ橋渡しする基底クラス.
 */
@Data
@Component
public class BaseControlDto {

    /**
     * ModelAndView
     */
    private ModelAndView mav;

    /**
     * パスパラメータ
     */
    private String pathNum;

    /**
     * バリデーションチェック結果
     */
    private BindingResult bindingResult;

    /**
     * リダイレクト時の値追加
     */
    private RedirectAttributes redirectAttributes;

    /**
     * エラーメッセージ
     */
    private String errorMsg;

}
