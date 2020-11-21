package com.devlogmh.www.domain.admin.service.imagesDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesForm;
import com.devlogmh.www.domain.model.session.SessionImagesData;
import com.devlogmh.www.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

/**
 * 画像管理 初期表示サービス
 */
@Service
@Transactional
public class ImagesDetailService extends AbsUtilService {

    /**
     * 画像管理 DBマッピング
     */
    @Autowired
    private ImagesMapper imagesMapper;

    /**
     * コントローラーからデータを受け取る
     */
    @Autowired
    private ImagesControlDto imagesControlDto;

    /**
     * 画像管理 アップロード用session
     */
    @Autowired
    private SessionImagesData sessionImagesData;

    private ImagesForm imagesForm;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.imagesForm = imagesControlDto.getImagesForm();
        this.mav = imagesControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // フォームの初期設定
        this.setupForm(this.imagesForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.imagesForm);

    }

    /**
     * フォームに入力された値を保持してフォームの初期設定
     * @param inputForm
     * @return
     */
    public void setupForm(ImagesForm inputForm) {

        // URL
        inputForm.setUrl(this.sessionImagesData.getUrl());

        // title
        inputForm.setTitle(this.sessionImagesData.getTitle());

        // alt
        inputForm.setAlt(inputForm.getAlt());

    }

}
