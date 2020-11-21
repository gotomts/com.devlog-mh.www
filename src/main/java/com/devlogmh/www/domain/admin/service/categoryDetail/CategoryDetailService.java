package com.devlogmh.www.domain.admin.service.categoryDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional
public class CategoryDetailService extends AbsUtilService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryControlDto categoryControlDto;

    private CategoryForm categoryForm;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.categoryForm = categoryControlDto.getCategoryForm();
        this.mav = categoryControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // フォームの初期設定
        this.setupForm(this.categoryForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.categoryForm);

    }

    /**
     * フォームに入力された値を保持してフォームの初期設定
     * @param inputForm
     * @return
     */
    public void setupForm(CategoryForm inputForm) {

        // カテゴリー名
        inputForm.setCategoryName(inputForm.getCategoryName());

    }

}
