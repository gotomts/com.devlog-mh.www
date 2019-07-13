package com.devlogmh.www.domain.admin.service.postDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostForm;
import com.devlogmh.www.mapper.CategoryMapper;
import com.devlogmh.www.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

import static com.devlogmh.www.domain.admin.util.Contains.DEFAULT_URL;

@Service
@Transactional
public class PostDetailService extends AbsUtilService {

    // ------------- Mapper -----------------

    /**
     * 投稿記事管理Mapper
     */
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * コントローラーからデータを受け取り
     */
    @Autowired
    private PostControlDto postControlDto;

    @Autowired
    private HttpServletRequest request;

    // ------------- Form -----------------

    private PostForm postForm;

    // ------------- mav -----------------

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.postForm = postControlDto.getPostForm();
        this.mav = postControlDto.getMav();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // フォームの初期設定
        this.setupForm(this.postForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.postForm);

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
        inputForm.setUrl(this.getUrl());
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
     * 新規作成時のURLを取得します。
     * @return
     */
    private String getUrl() {

        // 変数を初期化
        String url = null;

        // すでに存在する投稿記事のIDを検索
        List<Long> longList = postMapper.selectAllId();

        // IDが存在しなかったら1を設定して処理を終了
        if (0 == longList.size()) {
            url = DEFAULT_URL;
            return url;
        }

        // IDの最大値に+1して変数に格納
        Long maxId = Collections.max(longList) + 1;
        url = maxId.toString();

        return url;

    }

}
