package com.devlogmh.www.domain.service.usersService;

import com.devlogmh.www.domain.model.role.RoleEntity;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.repository.RoleRepository;
import com.devlogmh.www.domain.repository.UsersRepository;
import com.devlogmh.www.security.SessionData;
import com.devlogmh.www.util.Contains;
import com.devlogmh.www.util.TimestampUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SessionData sessionData;

    /**
     * パスワード暗号化処理
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * ユーザ情報をすべて検索します。
     * 更新者はIDからユーザ名に変換します。
     * @return ユーザ情報DTO
     */
    public List<UsersDto> init() {

        // ユーザー情報の一覧を取得
        List<UsersEntity> entityList = usersRepository.findAll();

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
        return usersRepository.getOne(id);
    }

    /**
     * 更新者の名称を取得します。
     */
    public String findUpdateUser(Long updater) {

        String updateUserName = null;

        updateUserName = this.findOne(updater).getUserName();

        return updateUserName;

    }

    /**
     * 新規登録/更新
     */
    public UsersEntity save(UsersEntity usersEntity) {
        // パスワードが空の場合は更新しない
        if (StringUtils.isNotEmpty(usersEntity.getPassword())) {
            // パスワードを暗号化してセット
            usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        }
        // 更新者
        usersEntity.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        usersEntity.setCreated(TimestampUtil.currentTime());
        // 更新時間
        usersEntity.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        usersEntity.setDelflg(Contains.DelFlg.NotDel.getValue());
        return usersRepository.save(usersEntity);
    }

    /**
     * 削除処理
     */
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }


    /**
     * 権限一覧
     */
    public List<RoleEntity> roleList() {
        List<RoleEntity> entityList = roleRepository.findAll();
        entityList.sort((a,b) -> (int) (a.getId() - b.getId()));
        return entityList;
    }

}
