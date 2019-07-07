package com.devlogmh.www.app.admin.images;

import com.devlogmh.www.domain.admin.service.images.ImagesService;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
