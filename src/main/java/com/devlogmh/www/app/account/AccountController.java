package com.devlogmh.www.app.account;

import com.devlogmh.www.domain.model.account.AccountEntity;
import com.devlogmh.www.domain.model.account.AccountForm;
import com.devlogmh.www.domain.service.account.AccountService;
import com.devlogmh.www.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * フォームに入力された値を取得するクラスを作成します。
     * @return
     */
    @ModelAttribute
    public AccountForm setupForm() {
        return new AccountForm();
    }

    /**
     * accountで接続した場合に表示するHTMLを設定します.
     * @return 表示するHTML
     */
    @RequestMapping(value="account")
    String accountForm() {
        return "account/accountForm";
    }

    /**
     * アカウントを作成
     * @param form
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "account", method = RequestMethod.POST)
    String create(@Validated AccountForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "account/accountForm";
        }
        // ユーザーエンティティをインスタンス化
        AccountEntity accountEntity = new AccountEntity();
        // ユーザー名を設定
        accountEntity.setName(form.getName());
        // メールアドレスを設定
        accountEntity.setEmail(form.getEmail());
        // パスワードをbcrypt方式でハッシュ化して設定
        accountService.create(accountEntity, form.getPassword());
        // 権限を設定
        accountEntity.setRole_id(1);
        // 更新者を設定
        accountEntity.setUpdater_id(1);
        // 作成日時を設定
        accountEntity.setCreated(TimestampUtil.currentTime());
        // 更新日時を設定
        accountEntity.setUpdated(TimestampUtil.currentTime());
        return "redirect:/account/complete";
    }

    /**
     * アカウント作成に整合した場合に遷移します。
     * @return
     */
    @RequestMapping(value = "account/complete", method = RequestMethod.GET)
    String createFinish() {
        return "account/accountForm";
    }

}
