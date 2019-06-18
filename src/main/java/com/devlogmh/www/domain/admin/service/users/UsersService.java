package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersListForm;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.devlogmh.www.domain.admin.util.Contains.PAGE_VIEW_SIZE;

@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * ユーザ情報をすべて検索します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public PagedListHolder<UsersDto> init(int id) {

        // ユーザー情報の一覧を取得
        List<UsersDto> dtoList = usersMapper.selectAll(0);

        return this.createPageList(dtoList, id);

    }

    public PagedListHolder<UsersDto> delList(int id) {

        // ユーザー情報の一覧を取得
        List<UsersDto> dtoList = usersMapper.selectAll(1);

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

    /**
     * ゴミ箱へ追加
     * @param usersListForm
     */
    public void trashAdd(UsersListForm usersListForm) {

        // Deleteフラグ
        usersListForm.setDelflg(Contains.DelFlg.DELETE.getId());
        usersMapper.trashMove(usersListForm);

    }

    /**
     * ゴミ箱から戻す
     * @param usersListForm
     */
    public void trashRemove(UsersListForm usersListForm) {

        // Deleteフラグ
        usersListForm.setDelflg(Contains.DelFlg.NOT_DEL.getId());
        usersMapper.trashMove(usersListForm);

    }


    /**
     * 削除処理
     */
    public void destroy(UsersListForm usersListForm) {
        // 削除処理
        usersMapper.destroy(usersListForm);
    }

}
