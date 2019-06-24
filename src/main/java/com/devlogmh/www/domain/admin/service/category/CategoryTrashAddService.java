package com.devlogmh.www.domain.admin.service.category;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.domain.model.category.CategoryListForm;
import com.devlogmh.www.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@Transactional
public class CategoryTrashAddService extends AbsUtilService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryControlDto categoryControlDto;

    private CategoryListForm categoryListForm;

    private BindingResult bindingResult;

    private ModelAndView mav;

    private RedirectAttributes redirectAttributes;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.categoryListForm = categoryControlDto.getCategoryListForm();
        this.bindingResult = categoryControlDto.getBindingResult();
        this.redirectAttributes = categoryControlDto.getRedirectAttributes();
    }
    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "ゴミ箱へ移動するカテゴリーを選択してください。";
            this.redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            return;

        }

        // formからdtoへ詰め替え
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDelflg(this.categoryListForm.getDelflg());
        categoryDto.setCheckId(this.categoryListForm.getCheckId());

        // チェックしたレコードをゴミ箱へ移動
        this.trashAdd(categoryDto);

    }

    /**
     * ゴミ箱へ追加
     * @param categoryDto
     */
    public void trashAdd(CategoryDto categoryDto) {

        // Deleteフラグ
        categoryDto.setDelflg(Contains.DelFlg.DELETE.getValue());
        categoryMapper.trashMove(categoryDto);

    }

}
