package com.devlogmh.www.domain.admin.service.categoryDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.domain.model.category.CategoryForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
@Transactional
public class CategoryDetailEditService extends AbsUtilService {

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

        // ユーザーIDを取得
        Long categoryId = categoryControlDto.getId();

        // フォームの初期設定
        this.setupForm(categoryId, this.categoryForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.categoryForm);

    }

    /**
     * IDをキーに検索し、フォームを初期設定
     * @return
     */
    public void setupForm(Long id, CategoryForm inputForm) {

        // ユーザ情報をIDをキーに1件検索
        CategoryDto categoryDto = categoryMapper.select(id);

        // カテゴリー名
        inputForm.setCategoryName(categoryDto.getCategoryName());

    }

}
