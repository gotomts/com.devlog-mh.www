package com.devlogmh.www.domain.admin.service.postDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.service.aws.AwsS3Service;
import com.devlogmh.www.domain.admin.util.Contains.DelFlg;
import com.devlogmh.www.domain.admin.util.FileUploadUtil;
import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostDto;
import com.devlogmh.www.domain.model.post.PostForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.exception.DuplicateProductException;
import com.devlogmh.www.mapper.CategoryMapper;
import com.devlogmh.www.mapper.PostMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.devlogmh.www.domain.admin.util.Contains.UPLOAD_FILE_NAME;

/**
 * 投稿記事管理 新規登録処理
 */
@Service
@Transactional
public class PostDetailCreateService extends AbsUtilService {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private HttpServletRequest request;

    // ------------- Mapper -----------------

    /**
     * 記事投稿管理マッパー
     */
    @Autowired
    private PostMapper postMapper;

    /**
     * カテゴリーマッパー
     */
    @Autowired
    private CategoryMapper categoryMapper;


    // ---

    /**
     * コントローラーからデータを受け取り
     */
    @Autowired
    private PostControlDto postControlDto;

    /**
     * AWS S3サービス
     */
    @Autowired
    private AwsS3Service awsS3Service;

    // ---

    private PostForm postForm;

    private BindingResult bindingResult;

    private ModelAndView mav;


    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.postForm = postControlDto.getPostForm();
        this.bindingResult = postControlDto.getBindingResult();
        this.mav = postControlDto.getMav();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // フォームの初期設定
        this.setupForm(this.postForm);

        // 独自バリデーションチェック実装
        this.validate(this.postForm, this.bindingResult);

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {
            // オブジェクトを詰め込み
            this.mav.addObject("form", this.postForm);
            return;
        }

        // 保存処理
        this.save(this.postForm);

    }

    /**
     * フォームに入力された値を保持してフォームの初期設定
     * @param inputForm
     * @return
     */
    public void setupForm(PostForm inputForm) {

        // rootPath
        inputForm.setRootPath(SiteInfoUtil.getRootPath(request));
        // URL
        inputForm.setUrl(inputForm.getUrl());
        // タイトル
        inputForm.setTitle(inputForm.getTitle());
        // キーワード
        inputForm.setKeyword(inputForm.getKeyword());
        // ディスクリプション
        inputForm.setDescription(inputForm.getDescription());
        // カテゴリーID
        inputForm.setCategoryId(inputForm.getCategoryId());
        // カテゴリー
        inputForm.setCategoryList(inputForm.getCategoryList(categoryMapper));
        // ステータスID
        inputForm.setStatusId(inputForm.getStatusId());
        // ステータス
        inputForm.setStatusList(inputForm.getSelectedStatuses());
        // アイキャッチ画像
        inputForm.setUploadFile(inputForm.getUploadFile());
        // コンテンツ
        inputForm.setContent(inputForm.getContent());

    }

    /**
     * 新規登録
     */
    public void save(PostForm inputForm) {

        // 保存用のエンティティインスタンスを生成
        PostDto postDto = new PostDto();

        // URL
        postDto.setUrl(inputForm.getUrl());
        // タイトル
        postDto.setTitle(inputForm.getTitle());
        // キーワード
        postDto.setKeyword(inputForm.getKeyword());
        // ディスクリプション
        postDto.setDescription(inputForm.getDescription());
        // カテゴリーID
        postDto.setCategoryId(inputForm.getCategoryId());
        // ステータスID
        postDto.setStatusId(inputForm.getStatusId());
        // コンテンツ
        postDto.setContent(inputForm.getContent());
        // 更新者
        postDto.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        postDto.setCreated(TimestampUtil.currentTime());
        // 更新時間
        postDto.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        postDto.setDelflg(DelFlg.NOT_DEL.getValue());

        // 画像アップロード
        FileUploadUtil.topImageUpload(awsS3Service, postDto, inputForm);

        postMapper.insert(postDto);
    }

    /**
     * 新規登録 バリデーションチェック
     * @param inputForm
     * @throws DuplicateProductException
     */
    public void validate(PostForm inputForm, BindingResult result) {

        // ユーザー管理エンティティから検索
        List<PostDto> dtoList = postMapper.selectAll();

        for (PostDto dto: dtoList) {

            // URLの重複チェック
            if (StringUtils.equals(dto.getUrl(), inputForm.getUrl())) {
                result.rejectValue("url", "duplicate", new String[]{"URL"}, "default message.");
            }

        }

        if (Objects.nonNull(result.getErrorCount())) {
            new DuplicateProductException();
        }

    }

}
