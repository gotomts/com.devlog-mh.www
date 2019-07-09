package com.devlogmh.www.domain.admin.service.images;

import com.devlogmh.www.domain.admin.service.aws.AwsS3Service;
import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.FileUploadUtil;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesDto;
import com.devlogmh.www.domain.model.images.ImagesListForm;
import com.devlogmh.www.domain.model.session.SessionImagesData;
import com.devlogmh.www.exception.DuplicateProductException;
import com.devlogmh.www.exception.MultipartFileIsEmptyException;
import com.devlogmh.www.mapper.ImagesMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

/**
 * 画像管理 アップロード処理
 */
@Service
@Transactional
public class ImagesUploadService extends AbsUtilService {

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
     * AWS S3サービス
     */
    @Autowired
    private AwsS3Service awsS3Service;

    /**
     * 画像管理 アップロード用session
     */
    @Autowired
    private SessionImagesData sessionImagesData;

    /**
     * 画像一覧管理フォーム
     */
    private ImagesListForm imagesListForm;

    /**
     * エラー結果
     */
    private BindingResult bindingResult;

    /**
     * ModelAndView
     */
    private ModelAndView mav;

    /**
     * リダイレクト時に値を追加
     */
    private RedirectAttributes redirectAttributes;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.imagesListForm = imagesControlDto.getImagesListForm();
        this.bindingResult = imagesControlDto.getBindingResult();
        this.redirectAttributes = imagesControlDto.getRedirectAttributes();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        this.validate(this.imagesListForm, this.bindingResult);

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "画像を選択し、アップロードしてください。";
            this.redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            return;

        }

        // DTOを生成
        ImagesDto imagesDto = new ImagesDto();

        // 画像名を自動生成
        String fileName = FileUploadUtil.getName(this.imagesListForm.getUploadFile().getOriginalFilename());

        // 画像のアップロード
        this.awsS3Service.upload(this.imagesListForm.getUploadFile(), fileName);

        // Title
        imagesDto.setTitle(fileName);
        // URL
        imagesDto.setUrl(awsS3Service.getUrl(fileName));

        // セッションに下記のデータをセット
        this.sessionImagesData.setTitle(imagesDto.getTitle());
        this.sessionImagesData.setUrl(imagesDto.getUrl());

    }

    /**
     * 新規登録 バリデーションチェック
     * @param inputForm
     * @throws DuplicateProductException
     */
    public void validate(ImagesListForm inputForm, BindingResult result) {

        // ファイルが選択された状態でアップロードしたか
        if (StringUtils.isEmpty(inputForm.getUploadFile().getOriginalFilename())) {
            result.rejectValue("uploadFile", "MultipartFileIsEmpty");
        }

        if (Objects.nonNull(result.getErrorCount())) {
            new MultipartFileIsEmptyException();
        }

    }

}
