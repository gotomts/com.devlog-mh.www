package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public List<UsersDto> init() {

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

        return resultList;

    }

    /**
     * ユーザ情報をIDをキーに1件検索
     * @param id
     * @return
     */
    public UsersEntity findOne(Long id) {
        return usersMapper.select(id);
    }

    /**
     * 更新者の名称を取得します。
     */
    public String findUpdateUser(Long updater) {

        // 変数を初期化
        String updateUserName = null;

        // 更新者名を設定
        updateUserName = this.findOne(updater).getUserName();

        return updateUserName;

    }

    /**
     * 削除処理
     */
//    public void delete(Long id) {
//        usersRepository.deleteById(id);
//    }

}
