package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersTrashAddService extends AbsUtilService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersControlDto usersControlDto;

    /**
     * 主処理
     * @return
     */
    public void mainProcess() {

        // エラーだった場合
        if (usersControlDto.getBindingResult().hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "ゴミ箱へ移動するユーザーを選択してください。";
            usersControlDto.getRedirectAttributes().addFlashAttribute("errorMsg", errorMsg);

            return;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(usersControlDto.getUsersListForm().getDelflg());
        usersDto.setCheckId(usersControlDto.getUsersListForm().getCheckId());

        // チェックしたレコードをゴミ箱へ移動
        this.trashAdd(usersDto);

    }

    /**
     * ゴミ箱へ追加
     * @param usersDto
     */
    public void trashAdd(UsersDto usersDto) {

        // Deleteフラグ
        usersDto.setDelflg(Contains.DelFlg.DELETE.getValue());
        usersMapper.trashMove(usersDto);

    }

}
