package com.devlogmh.www.domain.admin.service.imagesDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesDto;
import com.devlogmh.www.domain.model.images.ImagesForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional
public class ImagesDetailCreateService extends AbsUtilService {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private ImagesMapper imagesMapper;

    @Autowired
    private ImagesControlDto imagesControlDto;

    private ImagesForm imagesForm;

    private BindingResult bindingResult;

    private ModelAndView mav;

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

        // フォームの初期設定
        this.setupForm(this.imagesForm);

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {
            // オブジェクトを詰め込み
            this.mav.addObject("form", this.imagesForm);
            return;
        }

        // 保存処理
        this.save(this.imagesForm);

    }

    /**
     * フォームに入力された値を保持してフォームの初期設定
     * @param inputForm
     * @return
     */
    public void setupForm(ImagesForm inputForm) {

        // title
        inputForm.setTitle(inputForm.getTitle());
        // alt
        inputForm.setAlt(inputForm.getAlt());
        // url
        inputForm.setUrl(inputForm.getUrl());

    }

    /**
     * 新規登録
     */
    public void save(ImagesForm inputForm) {

        // 保存用のエンティティインスタンスを生成
        ImagesDto imagesDto = new ImagesDto();

        // title
        imagesDto.setTitle(inputForm.getTitle());
        // alt
        imagesDto.setAlt(inputForm.getAlt());
        // url
        imagesDto.setUrl(inputForm.getUrl());
        // 更新者
        imagesDto.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        imagesDto.setCreated(TimestampUtil.currentTime());
        // 更新時間
        imagesDto.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        imagesDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());

        imagesMapper.insert(imagesDto);
    }

}
