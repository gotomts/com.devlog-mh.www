package com.devlogmh.www.domain.admin.service.post;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostDto;
import com.devlogmh.www.mapper.PostMapper;
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
public class PostService extends AbsUtilService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostControlDto postControlDto;

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
        String errorMsg = postControlDto.getErrorMsg();
        // パスパラメータを取得
        String pathNum = postControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            postControlDto.getMav().addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<PostDto> pagedListHolder = this.init(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

    }

    /**
     * ユーザ情報を削除フラグの付いていないユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<PostDto> init(String id) {

        // ユーザー情報の一覧を取得
        List<PostDto> dtoList = postMapper.selectPostList(Contains.DelFlg.NOT_DEL.getValue());

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
            dto.setUrl(dto.getUrl());
            dto.setTitle(dto.getTitle());
            dto.setStatusName(dto.getStatusName(dto.getStatusId().toString()));
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
