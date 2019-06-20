package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.security.SessionData;
import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.mapper.UsersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.devlogmh.www.domain.admin.util.Contains.PAGE_VIEW_SIZE;

@Service
@Transactional
public class UsersService extends AbsUtilService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    @Autowired
    SessionData sessionData;

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(usersControlDto.getErrorMsg())) {
            usersControlDto.getMav().addObject("errorMsg", usersControlDto.getErrorMsg());
        }

        // サービスの初期処理
        PagedListHolder<UsersDto> pagedListHolder = this.init(usersControlDto.getPathNum());
        usersControlDto.getMav().addObject("pagedListHolder", pagedListHolder);

    }

    /**
     * ユーザ情報を削除フラグの付いていないユーザーを取得します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<UsersDto> init(int id) {

        // ユーザー情報の一覧を取得
        List<UsersDto> dtoList = usersMapper.selectAll(sessionData.getUserId(), Contains.DelFlg.NOT_DEL.getValue());

        return this.createPageList(dtoList, id);

    }

    /**
     *
     * @param dtoList
     * @param id
     * @return
     */
    public PagedListHolder<UsersDto> createPageList(List<UsersDto> dtoList, int id) {

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
        pagenation.setPage(id);
        // 1ページに表示するデータ数を設定
        pagenation.setPageSize(PAGE_VIEW_SIZE);

        return pagenation;

    }

}
