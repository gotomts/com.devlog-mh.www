package com.devlogmh.www.domain.admin.service.images;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.images.ImagesControlDto;
import com.devlogmh.www.domain.model.images.ImagesDto;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.mapper.ImagesMapper;
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
public class ImagesService extends AbsUtilService {

    @Autowired
    private ImagesMapper imagesMapper;

    @Autowired
    private ImagesControlDto imagesControlDto;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = this.imagesControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーメッセージを取得
        String errorMsg = imagesControlDto.getErrorMsg();
        // パスパラメータを取得
        String pathNum = imagesControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            imagesControlDto.getMav().addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<ImagesDto> pagedListHolder = this.init(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

    }

    /**
     * ユーザ情報を削除フラグの付いていないユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<ImagesDto> init(String id) {

        // ユーザー情報の一覧を取得
        List<ImagesDto> dtoList = imagesMapper.selectImagesList(Contains.DelFlg.NOT_DEL.getValue());

        return this.createPageList(dtoList, id);

    }

    /**
     * DTOに情報を詰めた後、ページャー用のオブジェクトに変換し、返します。
     * @param dtoList
     * @param id
     * @return ページャー用オブジェクトに変換
     */
    public PagedListHolder<ImagesDto> createPageList(List<ImagesDto> dtoList, String id) {

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (ImagesDto dto : dtoList) {

            // DTOに情報を詰める
            dto.setId(dto.getId());
            dto.setUrl(dto.getUrl());
            dto.setTitle(dto.getTitle());
            dto.setAlt(dto.getAlt());

        }

        // DTOをページャー用に変換
        PagedListHolder<ImagesDto> pagenation = new PagedListHolder<>(dtoList);
        // 現在のページ位置を渡す
        pagenation.setPage(new Integer(id));
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
