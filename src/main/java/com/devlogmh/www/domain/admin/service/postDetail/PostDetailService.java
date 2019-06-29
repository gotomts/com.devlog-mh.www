package com.devlogmh.www.domain.admin.service.postDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional
public class PostDetailService extends AbsUtilService {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostControlDto postControlDto;

    private PostForm postForm;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.postForm = postControlDto.getPostForm();
        this.mav = postControlDto.getMav();

        // ログイン情報を格納
        this.mav.addObject("isLogin", this.sessionData.isLogin());

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
        inputForm.setCategoryList(inputForm.getCategoryList());
        // ステータスID
        inputForm.setStatusId(inputForm.getStatusId());
        // ステータス
        inputForm.setStatusList(inputForm.getSelectedStatuses());
        // コンテンツ
        inputForm.setContent(inputForm.getContent());

    }

}
