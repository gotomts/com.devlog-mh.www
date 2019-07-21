package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.Pager;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.util.PagerUtil;
import com.devlogmh.www.mapper.UsersMapper;
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
public class UsersService extends AbsUtilService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SessionData sessionData;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {

        // コントローラーから渡された値を取得
        this.mav = this.usersControlDto.getMav();

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーメッセージを取得
        String errorMsg = this.usersControlDto.getErrorMsg();
        // パスパラメータを取得
        String pathNum = this.usersControlDto.getPathNum();
        if (Objects.isNull(pathNum)) {
            pathNum = "0";
            usersControlDto.setPathNum(pathNum);
        }


        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            this.usersControlDto.getMav().addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<UsersDto> pagedListHolder = this.init(pathNum);
        this.mav.addObject("pagedListHolder", pagedListHolder);

        // ページャーの設定
        Pager pager = PagerUtil.setupPager(pagedListHolder, this.usersControlDto, this.request);
        this.mav.addObject("pager", pager);

    }

    /**
     * ユーザ情報を削除フラグの付いていないユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<UsersDto> init(String id) {

        // ユーザー情報の一覧を取得
        List<UsersDto> dtoList = this.usersMapper.selectUserList(this.sessionData.getUserId(), Contains.DelFlg.NOT_DEL.getValue());

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
