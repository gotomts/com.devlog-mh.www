package com.devlogmh.www.domain.admin.service.postDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.SiteInfoUtil;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.CategoryMapper;
import com.devlogmh.www.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.devlogmh.www.domain.admin.util.Contains.DelFlg.NOT_DEL;

@Service
@Transactional
public class PostDetailService extends AbsUtilService {

    /**
     * session
     */
    @Autowired
    private SessionData sessionData;

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
        inputForm.setCategoryList(this.getCategoryList());
        // ステータスID
        inputForm.setStatusId(inputForm.getStatusId());
        // ステータス
        inputForm.setStatusList(inputForm.getSelectedStatuses());
        // コンテンツ
        inputForm.setContent(inputForm.getContent());

    }

    /**
     * カテゴリーの一覧を取得し、Mapに格納
     */
    public Map<String, String> getCategoryList() {

        // カテゴリー一覧を取得
        List<CategoryDto> categoryDtoList = this.categoryMapper.selectCategoryListOrderByCategoryName(NOT_DEL.getValue());

        Map<String, String> selectedCategoryList = new LinkedHashMap<>();

        // カテゴリーをプルダウン用のリストに詰める
        for (CategoryDto categoryDto : categoryDtoList) {
            selectedCategoryList.put(categoryDto.getId().toString(), categoryDto.getCategoryName());
        }

        return selectedCategoryList;

    }


}
