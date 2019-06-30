package com.devlogmh.www.domain.admin.service.postDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostDto;
import com.devlogmh.www.domain.model.post.PostForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.CategoryMapper;
import com.devlogmh.www.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class PostDetailEditService extends AbsUtilService {

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
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // ユーザーIDを取得
        Long categoryId = postControlDto.getId();

        // フォームの初期設定
        this.setupForm(categoryId, this.postForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.postForm);

        // ログイン情報を格納
        this.mav.addObject("isLogin", this.sessionData.isLogin());

    }

    /**
     * IDをキーに検索し、フォームを初期設定
     * @return
     */
    public void setupForm(Long id, PostForm inputForm) {

        // ユーザ情報をIDをキーに1件検索
        PostDto postDto = postMapper.select(id);

        // rootPath
        inputForm.setRootPath(SiteInfoUtil.getRootPath(request));
        // URL
        inputForm.setUrl(postDto.getUrl());
        // タイトル
        inputForm.setTitle(postDto.getTitle());
        // キーワード
        inputForm.setKeyword(postDto.getKeyword());
        // ディスクリプション
        inputForm.setDescription(postDto.getDescription());
        // カテゴリーID
        inputForm.setCategoryId(postDto.getCategoryId());
        // カテゴリー
        inputForm.setCategoryList(postDto.getCategoryList(categoryMapper));
        // ステータスID
        inputForm.setStatusId(postDto.getStatusId());
        // ステータス
        inputForm.setStatusList(postDto.getSelectedStatuses());
        // コンテンツ
        inputForm.setContent(postDto.getContent());

    }

}
