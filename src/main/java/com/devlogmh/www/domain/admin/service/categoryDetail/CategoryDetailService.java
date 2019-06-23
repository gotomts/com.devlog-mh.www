package com.devlogmh.www.domain.admin.service.categoryDetail;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.admin.util.TimestampUtil;
import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersForm;
import com.devlogmh.www.exception.DuplicateProductException;
import com.devlogmh.www.mapper.UsersMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CategoryDetailService extends AbsUtilService {

    @Autowired
    private SessionData sessionData;

    /**
     * パスワード暗号化処理
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    private UsersForm usersForm;

    private ModelAndView mav;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.usersForm = usersControlDto.getUsersForm();
        this.mav = usersControlDto.getMav();

        // ログイン情報を格納
        this.mav.addObject("isLogin", this.sessionData.isLogin());

    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // フォームの初期設定
        this.setupForm(this.usersForm);
        // オブジェクトを詰め込み
        this.mav.addObject("form", this.usersForm);

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
     * IDをキーに検索し、フォームを初期設定
     * @return
     */
    public void setupForm(Long id, UsersForm inputForm) {

        // ユーザ情報をIDをキーに1件検索
        UsersDto usersDto = usersMapper.select(id);

        // ユーザー名
        inputForm.setUserName(usersDto.getUserName());
        // メールアドレス
        inputForm.setEmail(usersDto.getEmail());
        // アカウント種類
        inputForm.setRoleId(usersDto.getRoleId());
        // アカウント種類リスト
        inputForm.setRoleList(inputForm.getSelectRoleItems());
        // パスワード
        inputForm.setPassword(usersDto.getPassword());

    }

    /**
     * 新規登録
     */
    public void save(UsersForm inputForm) {

        // 保存用のエンティティインスタンスを生成
        UsersDto usersDto = new UsersDto();

        // ユーザー名
        usersDto.setUserName(inputForm.getUserName());
        // メールアドレス
        usersDto.setEmail(inputForm.getEmail());
        // パスワードが空の場合は更新しない
        if (StringUtils.isNotEmpty(inputForm.getPassword())) {
            // パスワードを暗号化してセット
            usersDto.setPassword(passwordEncoder.encode(inputForm.getPassword()));
        }
        // ユーザー権限
        usersDto.setRoleId(inputForm.getRoleId());
        // 更新者
        usersDto.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        usersDto.setCreated(TimestampUtil.currentTime());
        // 更新時間
        usersDto.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        usersDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());

        usersMapper.insert(usersDto);
    }

    /**
     * 更新
     */
    public void update(UsersDto usersDto, UsersForm inputForm) {

        // ユーザー名
        usersDto.setUserName(inputForm.getUserName());
        // メールアドレス
        usersDto.setEmail(inputForm.getEmail());
        // パスワードが空の場合は更新しない
        if (StringUtils.isNotEmpty(inputForm.getPassword())) {
            // パスワードを暗号化してセット
            usersDto.setPassword(passwordEncoder.encode(inputForm.getPassword()));
        }
        // ユーザー権限
        usersDto.setRoleId(inputForm.getRoleId());
        // 更新者
        usersDto.setUpdaterId(sessionData.getUserId().longValue());
        // 登録時間
        usersDto.setCreated(TimestampUtil.currentTime());
        // 更新時間
        usersDto.setUpdated(TimestampUtil.currentTime());
        // 削除フラグ
        usersDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());

        usersMapper.update(usersDto);
    }

    /**
     * 新規登録 バリデーションチェック
     * @param inputForm
     * @throws DuplicateProductException
     */
    public void validate(UsersForm inputForm, BindingResult result) {

        // ユーザー管理エンティティから検索
        List<UsersDto> dtoList = usersMapper.selectUserList(sessionData.getUserId(), Contains.DelFlg.NOT_DEL.getValue());

        for (UsersDto dto: dtoList) {

            // ユーザー名の重複チェック
            if (StringUtils.equals(dto.getUserName(), inputForm.getUserName())) {
                result.rejectValue("userName", "duplicate", new String[]{"ユーザー名"}, "default message.");
            }

            // メールアドレスの重複チェック
            if (StringUtils.equals(dto.getEmail(), inputForm.getEmail())) {
                result.rejectValue("email", "duplicate", new String[]{"メールアドレス"}, "default message.");
            }

        }

        if (Objects.nonNull(result.getErrorCount())) {
            new DuplicateProductException();
        }

    }

    /**
     * 更新 バリデーションチェック
     * @param id, inputForm
     * @throws DuplicateProductException
     */
    public void validate(Long id, UsersForm inputForm, BindingResult result) {

        // 編集中のデータ
        UsersDto editData = usersMapper.select(id);

        // ユーザー管理エンティティから検索
        List<UsersDto> dtoList = usersMapper.selectUserList(sessionData.getUserId(), Contains.DelFlg.DELETE.getValue());

        for (UsersDto dto: dtoList) {

            // ユーザー名の重複チェック
            if (StringUtils.equals(dto.getUserName(), inputForm.getUserName())
                    && !StringUtils.equals(editData.getUserName(), inputForm.getUserName())) {
                result.rejectValue("userName", "duplicate", new String[]{"ユーザー名"}, "default message.");
            }

            // メールアドレスの重複チェック
            if (StringUtils.equals(dto.getEmail(), inputForm.getEmail())
                    && !StringUtils.equals(editData.getEmail(), inputForm.getEmail())) {
                result.rejectValue("email", "duplicate", new String[]{"メールアドレス"}, "default message.");
            }

        }

        if (Objects.nonNull(result.getErrorCount())) {
            new DuplicateProductException();
        }

    }

}
