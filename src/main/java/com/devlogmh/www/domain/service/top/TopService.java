package com.devlogmh.www.domain.service.top;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.blog.BlogControlDto;
import com.devlogmh.www.domain.model.blog.BlogDisplay;
import com.devlogmh.www.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

import static com.devlogmh.www.domain.admin.util.Contains.PAGE_VIEW_SIZE;

/**
 * トップページ初期画面 サービス
 */
@Service
@Transactional
public class TopService extends AbsUtilService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogControlDto blogControlDto;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = this.blogControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // パスパラメータを取得
        String pathNum = blogControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }

        // サービスの初期処理
        PagedListHolder<BlogDisplay> pagedListHolder = this.init(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

    }

    /**
     * ブログ記事取得します。<br />
     * @return ブログ記事DTO
     */
    public PagedListHolder<BlogDisplay> init(String id) {

        // ブログ記事を取得
        List<BlogDisplay> dtoList = blogMapper.selectBlogDisplayList();

        return this.createPageList(dtoList, id);

    }

    /**
     * 記事リストをページャーが作れるリストに変換します。
     * @param dtoList
     * @param id
     * @return pagenation
     */
    public PagedListHolder<BlogDisplay> createPageList(List<BlogDisplay> dtoList, String id) {

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (BlogDisplay dto : dtoList) {

            // DTOに情報を詰める
            dto.setId(dto.getId());
            dto.setTitle(dto.getTitle());
            dto.setUrl(dto.getUrl());
            dto.setCategoryName(dto.getCategoryName());
            dto.setContent(dto.getContent());
            dto.setTopImageUrl(dto.getTopImageUrl());
            dto.setTopImageTitle(dto.getTopImageTitle());
            dto.setTopImageAlt(dto.getTopImageAlt());

        }

        // DTOをページャー用に変換
        PagedListHolder<BlogDisplay> pagenation = new PagedListHolder<>(dtoList);
        // 現在のページ位置を渡す
        pagenation.setPage(new Integer(id));
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
