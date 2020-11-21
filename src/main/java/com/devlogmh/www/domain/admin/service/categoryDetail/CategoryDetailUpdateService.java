package com.devlogmh.www.domain.admin.service.categoryDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.domain.model.category.CategoryForm;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.exception.DuplicateProductException;
import com.devlogmh.www.mapper.CategoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CategoryDetailUpdateService extends AbsUtilService {

    @Autowired
    private SessionData sessionData;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryControlDto categoryControlDto;

    private CategoryForm categoryForm;

    private BindingResult bindingResult;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.categoryForm = categoryControlDto.getCategoryForm();
        this.bindingResult = categoryControlDto.getBindingResult();
        this.mav = categoryControlDto.getMav();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // カテゴリーIDを取得
        Long id = categoryControlDto.getId();

        // エンティティ生成
        CategoryDto categoryDto = new CategoryDto();

        this.validate(id, this.categoryForm, this.bindingResult);

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {
            // オブジェクトを詰め込み
            this.mav.addObject("form", this.categoryForm);
            return;
        }

        // 更新する対象のIDを設定
        categoryDto.setId(id);

        // 更新処理
        this.update(categoryDto, this.categoryForm);

    }

    /**
     * 更新
     */
    public void update(CategoryDto categoryDto, CategoryForm inputForm) {

        // カテゴリー名
        categoryDto.setCategoryName(inputForm.getCategoryName());
        // 更新者
        categoryDto.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        categoryDto.setCreated(TimestampUtil.currentTime());
        // 更新時間
        categoryDto.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        categoryDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());

        categoryMapper.update(categoryDto);
    }

    /**
     * 更新 バリデーションチェック
     * @param categoryId, inputForm
     * @throws DuplicateProductException
     */
    public void validate(Long categoryId, CategoryForm inputForm, BindingResult result) {

        // 編集中のデータ
        CategoryDto editData = categoryMapper.select(categoryId);

        // ユーザー管理エンティティから検索
        List<CategoryDto> dtoList = categoryMapper.selectAll();

        for (CategoryDto dto: dtoList) {

            // カテゴリー名の重複チェック
            if (StringUtils.equals(dto.getCategoryName(), inputForm.getCategoryName())
                    && !StringUtils.equals(editData.getCategoryName(), inputForm.getCategoryName())) {
                result.rejectValue("categoryName", "duplicate", new String[]{"カテゴリー名"}, "default message.");
            }

        }

        if (Objects.nonNull(result.getErrorCount())) {
            new DuplicateProductException();
        }

    }

}
