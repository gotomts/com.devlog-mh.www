package com.devlogmh.www.domain.admin.service.category;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.category.CategoryControlDto;
import com.devlogmh.www.domain.model.category.CategoryDto;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.CategoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

import static com.devlogmh.www.domain.admin.util.Contains.PAGE_VIEW_SIZE;

@Service
@Transactional
public class CategoryTrashListService extends AbsUtilService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryControlDto categoryControlDto;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = this.categoryControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーメッセージを取得
        String errorMsg = this.categoryControlDto.getErrorMsg();
        // パスパラメータを取得
        String pathNum = this.categoryControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            this.mav.addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<CategoryDto> pagedListHolder = this.delList(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

    }

    /**
     * ユーザ情報を削除フラグの付いているユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<CategoryDto> delList(String id) {

        // ユーザー情報の一覧を取得
        List<CategoryDto> dtoList = this.categoryMapper.selectCategoryList(Contains.DelFlg.DELETE.getValue());

        return this.createPageList(dtoList, id);

    }


    /**
     *
     * @param dtoList
     * @param id
     * @return
     */
    public PagedListHolder<CategoryDto> createPageList(List<CategoryDto> dtoList, String id) {

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (CategoryDto dto : dtoList) {

            // DTOに情報を詰める
            dto.setId(dto.getId());
            dto.setCategoryName(dto.getCategoryName());
            dto.setUpdaterName(dto.getUpdaterName());
            dto.setUpdateTime(dto.getUpdateTime(dto.getUpdated()));

        }

        // DTOをページャー用に変換
        PagedListHolder<CategoryDto> pagenation = new PagedListHolder<>(dtoList);
        // 現在のページ位置を渡す
        pagenation.setPage(new Integer(id));
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
