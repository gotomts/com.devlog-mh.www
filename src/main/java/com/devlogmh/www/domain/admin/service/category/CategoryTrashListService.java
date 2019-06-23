package com.devlogmh.www.domain.admin.service.category;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.mapper.UsersMapper;
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
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    @Autowired
    SessionData sessionData;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = usersControlDto.getMav();

        // ログイン情報を格納
        this.mav.addObject("isLogin", this.sessionData.isLogin());

    }
    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーメッセージを取得
        String errorMsg = usersControlDto.getErrorMsg();
        // パスパラメータを取得
        String pathNum = usersControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
        }

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            this.mav.addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<UsersDto> pagedListHolder = this.delList(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

    }

    /**
     * ユーザ情報を削除フラグの付いているユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<UsersDto> delList(String id) {

        // ユーザー情報の一覧を取得
        List<UsersDto> dtoList = usersMapper.selectUserList(sessionData.getUserId(), Contains.DelFlg.DELETE.getValue());

        return this.createPageList(dtoList, id);

    }


    /**
     *
     * @param dtoList
     * @param id
     * @return
     */
    public PagedListHolder<UsersDto> createPageList(List<UsersDto> dtoList, String id) {

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (UsersDto dto : dtoList) {

            // エンティティからそれぞれの情報を取得
            String updateTime = TimestampUtil.formattedTimestamp(dto.getUpdated(), Contains.TIME_FORMAT);

            // DTOに情報を詰める
            dto.setId(dto.getId());
            dto.setUserName(dto.getUserName());
            dto.setEmail(dto.getEmail());
            dto.setUpdaterName(dto.getUpdaterName());
            dto.setUpdateTime(updateTime);

        }

        // DTOをページャー用に変換
        PagedListHolder<UsersDto> pagenation = new PagedListHolder<>(dtoList);
        // 現在のページ位置を渡す
        pagenation.setPage(new Integer(id));
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
