package com.devlogmh.www.domain.admin.service.postDetail;

import com.devlogmh.www.domain.admin.service.aws.AwsS3Service;
import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
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
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PostDetailUpdateService extends AbsUtilService {

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

        // カテゴリーIDを取得
        Long id = postControlDto.getId();

        // フォームの初期設定
        this.setupForm(this.postForm);

        // エンティティ生成
        PostDto postDto = new PostDto();

        this.validate(id, this.postForm, this.bindingResult);

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {
            // オブジェクトを詰め込み
            this.mav.addObject("form", this.postForm);
            return;
        }

        // 更新する対象のIDを設定
        postDto.setId(id);

        // 更新処理
        this.update(postDto, this.postForm);

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
        // アイキャッチ画像/URL
        inputForm.setTopImageUrl(inputForm.getTopImageUrl());
        // アイキャッチ画像/title
        inputForm.setTopImageTitle(inputForm.getTopImageTitle());
        // アイキャッチ画像/alt
        inputForm.setTopImageAlt(inputForm.getTopImageAlt());
        // コンテンツ
        inputForm.setContent(inputForm.getContent());

    }

    /**
     * 更新
     */
    public void update(PostDto postDto, PostForm inputForm) {

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
        postDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());

        // 画像アップロード
        FileUploadUtil.topImageUpload(awsS3Service , postDto, inputForm);

        postMapper.update(postDto);
    }

    /**
     * 更新 バリデーションチェック
     * @param postId, inputForm
     * @throws DuplicateProductException
     */
    public void validate(Long postId, PostForm inputForm, BindingResult result) {

        // 編集中のデータ
        PostDto editData = postMapper.select(postId);

        // ユーザー管理エンティティから検索
        List<PostDto> dtoList = postMapper.selectAll();

        for (PostDto dto: dtoList) {

            // URLの重複チェック
            if (StringUtils.equals(dto.getUrl(), inputForm.getUrl())
                    && !StringUtils.equals(editData.getUrl(), inputForm.getUrl())) {
                result.rejectValue("url", "duplicate", new String[]{"URL"}, "default message.");
            }

        }

        if (Objects.nonNull(result.getErrorCount())) {
            new DuplicateProductException();
        }

    }



}
