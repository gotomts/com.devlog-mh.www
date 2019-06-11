package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.model.users.UsersEntity;
import com.devlogmh.www.domain.model.users.UsersForm;
import com.devlogmh.www.domain.repository.RoleRepository;
import com.devlogmh.www.domain.repository.UsersRepository;
import com.devlogmh.www.exception.DuplicateProductException;
import com.devlogmh.www.domain.admin.security.SessionData;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersFormService {

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
     * IDをキーに検索し、フォームを初期設定
     * @return
     */
    public void setupForm(Long id, UsersForm inputForm) {

        // ユーザ情報をIDをキーに1件検索
        UsersEntity entity = this.findOne(id);

        // ユーザー名
        inputForm.setUserName(entity.getUserName());
        // メールアドレス
        inputForm.setEmail(entity.getEmail());
        // アカウント種類
        inputForm.setRoleId(entity.getRoleId());
        // アカウント種類リスト
        inputForm.setRoleList(inputForm.getSelectRoleItems());
        // パスワード
        inputForm.setPassword(entity.getPassword());

    }

    /**
     * フォームに入力された値を保持してフォームの初期設定
     * @param inputForm
     * @return
     */
    public void setupForm(UsersForm inputForm) {

        // ユーザー名
        inputForm.setUserName(inputForm.getUserName());
        // メールアドレス
        inputForm.setEmail(inputForm.getEmail());
        // アカウント種類
        inputForm.setRoleId(inputForm.getRoleId());
        // アカウント種類リスト
        inputForm.setRoleList(inputForm.getSelectRoleItems());
        // パスワード
        inputForm.setPassword(inputForm.getPassword());

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
     * 新規登録
     */
    public UsersEntity save(UsersForm inputForm) {

        // 保存用のエンティティインスタンスを生成
        UsersEntity entity = new UsersEntity();

        // ユーザー名
        entity.setUserName(inputForm.getUserName());
        // メールアドレス
        entity.setEmail(inputForm.getEmail());
        // パスワードが空の場合は更新しない
        if (StringUtils.isNotEmpty(inputForm.getPassword())) {
            // パスワードを暗号化してセット
            entity.setPassword(passwordEncoder.encode(inputForm.getPassword()));
        }
        // ユーザー権限
        entity.setRoleId(inputForm.getRoleId());
        // 更新者
        entity.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        entity.setCreated(TimestampUtil.currentTime());
        // 更新時間
        entity.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        entity.setDelflg(Integer.parseInt(Contains.DelFlg.NotDel.getValue()));

        return usersRepository.save(entity);
    }

    /**
     * 更新
     */
    public UsersEntity update(UsersEntity entity, UsersForm inputForm) {

        // ユーザー名
        entity.setUserName(inputForm.getUserName());
        // メールアドレス
        entity.setEmail(inputForm.getEmail());
        // パスワードが空の場合は更新しない
        if (StringUtils.isNotEmpty(inputForm.getPassword())) {
            // パスワードを暗号化してセット
            entity.setPassword(passwordEncoder.encode(inputForm.getPassword()));
        }
        // ユーザー権限
        entity.setRoleId(inputForm.getRoleId());
        // 更新者
        entity.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        entity.setCreated(TimestampUtil.currentTime());
        // 更新時間
        entity.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        entity.setDelflg(Integer.parseInt(Contains.DelFlg.NotDel.getValue()));

        return usersRepository.save(entity);
    }

    /**
     * 新規登録 バリデーションチェック
     * @param inputForm
     * @throws DuplicateProductException
     */
    public void validate(UsersForm inputForm) throws DuplicateProductException {

        // ユーザー管理エンティティから検索
        List<UsersEntity> entityList = usersRepository.findAll();

        for (UsersEntity entity: entityList) {
            // メールアドレスの重複チェック
            if (StringUtils.equals(entity.getEmail(), inputForm.getEmail())) {
                throw new DuplicateProductException();
            }
        }

    }

    /**
     * 更新 バリデーションチェック
     * @param id, inputForm
     * @throws DuplicateProductException
     */
    public void validate(Long id, UsersForm inputForm) throws DuplicateProductException {

        // 編集中のデータ
        UsersEntity editData = this.findOne(id);

        // ユーザー管理エンティティから検索
        List<UsersEntity> entityList = usersRepository.findAll();

        for (UsersEntity entity: entityList) {
            // メールアドレスの重複チェック
            if (StringUtils.equals(entity.getEmail(), inputForm.getEmail())
                    && !StringUtils.equals(editData.getEmail(), inputForm.getEmail())) {
                throw new DuplicateProductException();
            }
        }

    }

}
