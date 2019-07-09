package com.devlogmh.www.app.admin.images;

import com.devlogmh.www.domain.admin.service.imagesDetail.ImagesDetailService;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.devlogmh.www.domain.admin.util.RoutesContains.IMAGES_NEW;

/**
 * 画像管理フォームコントローラー
 */
@Controller
@RequestMapping("/admin/images")
public class ImagesDetailController {

    /*------------ DI ---------------*/

    /** コントローラーからサービスへの橋渡し */
    @Autowired
    private ImagesControlDto imagesControlDto;

    /** 新規作成 初期表示 */
    @Autowired
    private ImagesDetailService imagesDetailService;

//    /** 新規作成 登録処理 */
//    @Autowired
//    private CategoryDetailCreateService categoryDetailCreateService;
//
//    /** 編集画面 初期表示 */
//    @Autowired
//    private CategoryDetailEditService categoryDetailEditService;
//
//    /** 編集画面 更新処理 */
//    @Autowired
//    private CategoryDetailUpdateService categoryDetailUpdateService;

    /**
     * アップロード後の画像管理登録画面 初期表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView index(ImagesForm inputForm, ModelAndView mav) {

        // 画像管理詳細フォーム
        imagesControlDto.setImagesForm(inputForm);
        // ModelAndView
        imagesControlDto.setMav(mav);

        // 初期表示
        imagesDetailService.delegate(imagesControlDto);

        // ビューの設定
        mav.setViewName(IMAGES_NEW);
        return mav;

    }

//    /**
//     * 新規作成 登録処理
//     * @param inputForm
//     * @return
//     */
//    @PostMapping("new")
//    public ModelAndView create(@ModelAttribute("form") @Validated ImagesForm inputForm, BindingResult result, ModelAndView mav) {
//
//        // カテゴリーマスタ詳細フォーム
//        imagesControlDto.setImagesForm(inputForm);
//        // ModelAndView
//        imagesControlDto.setMav(mav);
//        // バリデーションエラー結果
//        imagesControlDto.setBindingResult(result);
//
//        // 新規作成 登録処理
//        categoryDetailCreateService.delegate(imagesControlDto);
//
//        // エラーがあった場合
//        if (result.hasErrors()) {
//            // ビューの設定
//            mav.setViewName(IMAGES_NEW);
//            return mav;
//        }
//
//        // リダイレクト設定
//        mav = new ModelAndView(REDIRECT_IMAGES_LIST);
//        return mav;
//    }
//
//    /**
//     * 編集画面 表示
//     */
//    @GetMapping("edit/{id}")
//    public ModelAndView edit(@PathVariable Long id, ImagesForm inputForm, ModelAndView mav) {
//
//        // カテゴリーID
//        imagesControlDto.setId(id);
//        // カテゴリーマスタ詳細フォーム
//        imagesControlDto.setImagesForm(inputForm);
//        // ModelAndView
//        imagesControlDto.setMav(mav);
//
//        // フォームの初期設定
//        categoryDetailEditService.delegate(imagesControlDto);
//
//        // ビューの設定
//        mav.setViewName(IMAGES_EDIT);
//        return mav;
//    }
//
//    /**
//     * 編集画面 更新処理
//     */
//    @PutMapping("edit/{id}")
//    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated ImagesForm inputForm, BindingResult result, ModelAndView mav) {
//
//        // カテゴリーID
//        imagesControlDto.setId(id);
//        // カテゴリーマスタ詳細フォーム
//        imagesControlDto.setImagesForm(inputForm);
//        // ModelAndView
//        imagesControlDto.setMav(mav);
//        // バリデーションエラー結果
//        imagesControlDto.setBindingResult(result);
//
//        // 新規作成 登録処理
//        categoryDetailUpdateService.delegate(imagesControlDto);
//
//        // エラーがあった場合
//        if (result.hasErrors()) {
//            // ビューの設定
//            mav.setViewName(REDIRECT_IMAGES_LIST);
//            return mav;
//        }
//
//        // 更新後のリダイレクト先
//        mav = new ModelAndView(REDIRECT_IMAGES_LIST);
//        return mav;
//    }


}
