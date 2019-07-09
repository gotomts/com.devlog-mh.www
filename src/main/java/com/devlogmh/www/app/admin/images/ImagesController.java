package com.devlogmh.www.app.admin.images;

import com.devlogmh.www.domain.admin.service.images.ImagesService;
import com.devlogmh.www.domain.admin.service.images.ImagesUploadService;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_IMAGES_LIST;
import static com.devlogmh.www.domain.admin.util.RedirectContains.REDIRECT_IMAGES_UPLOAD;
import static com.devlogmh.www.domain.admin.util.RoutesContains.IMAGES_LIST;

/**
 * 画像管理コントローラー
 */
@Controller
@RequestMapping("/admin/images")
public class ImagesController {

    /*------------ DI ---------------*/

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private ImagesControlDto imagesControlDto;

    /** 一覧 */
    @Autowired
    private ImagesService imagesService;

    /** アップロード処理 */
    @Autowired
    private ImagesUploadService imagesUploadService;

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping({"", "{id}"})
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg, @PathVariable(name = "id", required = false) String id, ModelAndView mav) {

        // エラーメッセージ
        this.imagesControlDto.setErrorMsg(errorMsg);
        // パスパラメータ
        this.imagesControlDto.setPathNum(id);
        // ModelAndView
        this.imagesControlDto.setMav(mav);

        // 一覧表示処理
        this.imagesService.delegate(this.imagesControlDto);

        // ビューの設定
        mav.setViewName(IMAGES_LIST);
        return mav;

    }

    /**
     * 画像アップロード処理
     * @param inputForm
     * @param result
     * @param mav
     * @param redirectAttributes
     * @return mav
     */
    @PostMapping("new")
    public ModelAndView upload(@ModelAttribute("form") @Validated ImagesListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // 画像アップロードフォーム
        imagesControlDto.setImagesListForm(inputForm);
        // ModelAndView
        imagesControlDto.setMav(mav);
        // バリデーションエラー結果
        imagesControlDto.setBindingResult(result);
        // リダイレクト時に値を渡す
        imagesControlDto.setRedirectAttributes(redirectAttributes);

        // アップロード画面表示
        this.imagesUploadService.delegate(this.imagesControlDto);

        // エラーがあった場合
        if (result.hasErrors()) {
            // 画像管理一覧画面へリダイレクト
            mav.setViewName(REDIRECT_IMAGES_LIST);
            return mav;
        }

        // アップロード後の登録画面へ遷移
        mav.setViewName(REDIRECT_IMAGES_UPLOAD);
        return mav;

    }

}
