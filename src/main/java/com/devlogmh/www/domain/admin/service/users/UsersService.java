package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<UsersDto> dtoList = usersMapper.selectAll();

        // ユーザー情報DTOリストのインスタンス生成
        List<UsersDto> resultList = new ArrayList<UsersDto>();

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

            // リストに追加
            resultList.add(dto);

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
     * ページネーション処理
     * @param dtoList
     * @param p
     * @return
     */
    public PagedListHolder<UsersDto> pagedListHolder(List<UsersDto> dtoList, String p) {

        PagedListHolder<UsersDto> pagenation = new PagedListHolder<>(dtoList);
        pagenation.setPage(Integer.parseInt(p));
        pagenation.setPageSize(2);

        return pagenation;

    }

    /**
     * 削除処理
     */
//    public void delete(Long id) {
//        usersRepository.deleteById(id);
//    }

}
