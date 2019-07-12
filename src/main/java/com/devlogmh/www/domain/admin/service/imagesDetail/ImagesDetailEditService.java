package com.devlogmh.www.domain.admin.service.imagesDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesDto;
import com.devlogmh.www.domain.model.images.ImagesForm;
import com.devlogmh.www.domain.model.session.SessionImagesData;
import com.devlogmh.www.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

/**
 * 画像管理 編集サービス
 */
@Service
@Transactional
public class ImagesDetailEditService extends AbsUtilService {

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
        this.imagesForm = this.imagesControlDto.getImagesForm();
        this.mav = this.imagesControlDto.getMav();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // ユーザーIDを取得
        Long categoryId = this.imagesControlDto.getId();

        // フォームの初期設定
        this.setupForm(categoryId, this.imagesForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.imagesForm);

    }

    /**
     * IDをキーに検索し、フォームを初期設定
     * @return
     */
    public void setupForm(Long id, ImagesForm inputForm) {

        // ユーザ情報をIDをキーに1件検索
        ImagesDto imagesDto = imagesMapper.select(id);

        // URL
        inputForm.setUrl(imagesDto.getUrl());

        // title
        inputForm.setTitle(imagesDto.getTitle());

        // alt
        inputForm.setAlt(imagesDto.getAlt());


    }

}
