package com.devlogmh.www.domain.admin.service.imagesDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesDto;
import com.devlogmh.www.domain.model.images.ImagesForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.session.SessionImagesData;
import com.devlogmh.www.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * 画像管理 更新処理
 */
@Service
@Transactional
public class ImagesDetailUpdateService extends AbsUtilService {

    /**
     * ログイン Session情報
     */
    @Autowired
    private SessionData sessionData;

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

    private BindingResult bindingResult;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.imagesForm = imagesControlDto.getImagesForm();
        this.bindingResult = imagesControlDto.getBindingResult();
        this.mav = imagesControlDto.getMav();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // IDを取得
        Long id = imagesControlDto.getId();

        // エンティティ生成
        ImagesDto imagesDto = new ImagesDto();

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {
            // オブジェクトを詰め込み
            this.mav.addObject("form", this.imagesForm);
            return;
        }

        // 更新する対象のIDを設定
        imagesDto.setId(id);

        // 更新処理
        this.update(imagesDto, this.imagesForm);

    }

    /**
     * 更新
     */
    public void update(ImagesDto imagesDto, ImagesForm inputForm) {

        // URL
        imagesDto.setUrl(inputForm.getUrl());
        // title
        imagesDto.setTitle(inputForm.getTitle());
        // alt
        imagesDto.setAlt(inputForm.getAlt());
        // 更新者
        imagesDto.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        imagesDto.setCreated(TimestampUtil.currentTime());
        // 更新時間
        imagesDto.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        imagesDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());

        imagesMapper.update(imagesDto);
    }

}
