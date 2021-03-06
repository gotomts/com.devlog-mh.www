package com.devlogmh.www.domain.admin.service.post;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.Pager;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostDto;
import com.devlogmh.www.domain.util.PagerUtil;
import com.devlogmh.www.mapper.PostMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.devlogmh.www.domain.admin.util.Contains.PAGE_VIEW_SIZE;

@Service
@Transactional
public class PostTrashListService extends AbsUtilService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostControlDto postControlDto;

    @Autowired
    private HttpServletRequest request;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = this.postControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーメッセージを取得
        String errorMsg = this.postControlDto.getErrorMsg();
        // パスパラメータを取得
        String pathNum = this.postControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
            postControlDto.setPathNum(pathNum);
        }

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            this.mav.addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<PostDto> pagedListHolder = this.delList(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

        // ページャーの設定
        Pager pager = PagerUtil.setupPager(pagedListHolder, this.postControlDto, this.request);
        this.mav.addObject("pager", pager);

    }

    /**
     * ユーザ情報を削除フラグの付いているユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @rn ユーザ情報DTO
     */
    public PagedListHolder<PostDto> delList(String id) {

        // ユーザー情報の一覧を取得
        List<PostDto> dtoList = this.postMapper.selectPostList(Contains.DelFlg.DELETE.getValue());

        return this.createPageList(dtoList, id);

    }


    /**
     *
     * @param dtoList
     * @param id
     * @return
     */
    public PagedListHolder<PostDto> createPageList(List<PostDto> dtoList, String id) {

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (PostDto dto : dtoList) {

            // DTOに情報を詰める
            dto.setId(dto.getId());
            dto.setCategoryName(dto.getCategoryName());
            dto.setUpdaterName(dto.getUpdaterName());
            dto.setUpdateTime(dto.getUpdateTime(dto.getUpdated()));

        }

        // DTOをページャー用に変換
        PagedListHolder<PostDto> pagenation = new PagedListHolder<>(dtoList);
        // 現在のページ位置を渡す
        pagenation.setPage(new Integer(id));
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
