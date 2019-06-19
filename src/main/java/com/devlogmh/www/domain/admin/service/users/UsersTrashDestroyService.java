package com.devlogmh.www.domain.admin.service.users;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.model.users.UsersControlDto;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersTrashDestroyService extends AbsUtilService {

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
            String errorMsg = "削除するユーザーを選択してください。";
            usersControlDto.getRedirectAttributes().addFlashAttribute("errorMsg", errorMsg);

            return;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(usersControlDto.getUsersListForm().getDelflg());
        usersDto.setCheckId(usersControlDto.getUsersListForm().getCheckId());

        // チェックしたレコードをゴミ箱へ移動
        this.trashDestroy(usersDto);

    }

    /**
     * ゴミ箱 削除処理
     */
    public void trashDestroy(UsersDto usersDto) {
        // 削除処理
        usersMapper.destroy(usersDto);
    }


}
