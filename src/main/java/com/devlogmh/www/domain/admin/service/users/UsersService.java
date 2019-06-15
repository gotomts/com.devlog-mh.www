package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsersService {

    private final UsersMapper usersMapper;

    /**
     * デフォルトコンストラクタ
     * @param usersMapper
     */
    public UsersService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * ユーザ情報をすべて検索します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public List<UsersDto> init() {

        // ユーザー情報の一覧を取得
        List<UsersEntity> entityList = usersMapper.selectAll();

        // ユーザー情報DTOリストのインスタンス生成
        List<UsersDto> dtoList = new ArrayList<UsersDto>();

        // ユーザー情報を1件ずつ取り出してDTOに格納
        for (UsersEntity entity : entityList) {

            // ユーザー情報DTOのインスタンス生成
            UsersDto dto = new UsersDto();

            // エンティティからそれぞれの情報を取得
            String updaterName = this.findUpdateUser(entity.getUpdaterId().longValue());
            String updateTime = TimestampUtil.formattedTimestamp(entity.getUpdated(), Contains.TIME_FORMAT);

            // DTOに情報を詰める
            dto.setId(entity.getId());
            dto.setUserName(entity.getUserName());
            dto.setEmail(entity.getEmail());
            dto.setUpdaterName(updaterName);
            dto.setUpdateTime(updateTime);

            // リストに追加
            dtoList.add(dto);

        }

        return dtoList;

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
