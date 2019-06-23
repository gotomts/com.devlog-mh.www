package com.devlogmh.www.domain.admin.service.category;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersListForm;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
@Transactional
public class CategoryTrashRemoveService extends AbsUtilService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    private UsersListForm usersListForm;

    private BindingResult bindingResult;

    private RedirectAttributes redirectAttributes;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.usersListForm = usersControlDto.getUsersListForm();
        this.bindingResult = usersControlDto.getBindingResult();
        this.redirectAttributes = usersControlDto.getRedirectAttributes();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "ゴミ箱へ移動するユーザーを選択してください。";
            this.redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            return;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(this.usersListForm.getDelflg());
        usersDto.setCheckId(this.usersListForm.getCheckId());

        // チェックしたレコードをゴミ箱へ移動
        this.trashRemove(usersDto);

    }

    /**
     * ゴミ箱から戻す
     * @param usersDto
     */
    public void trashRemove(UsersDto usersDto) {

        // Deleteフラグ
        usersDto.setDelflg(Contains.DelFlg.NOT_DEL.getValue());
        usersMapper.trashMove(usersDto);

    }

}
