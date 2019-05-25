package com.devlogmh.www.app.account;

import com.devlogmh.www.domain.model.Users.UsersEntity;
import com.devlogmh.www.domain.model.account.AccountForm;
import com.devlogmh.www.domain.service.account.AccountService;
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

    @RequestMapping(value = "account", method = RequestMethod.POST)
    String create(@Validated AccountForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "account/accountForm";
        }
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(form.getEmail());
        accountService.create(usersEntity, form.getPassword());
        return "redirect:/account/complete";
    }

    @RequestMapping(value = "account/complete", method = RequestMethod.GET)
    String createFinish() {
        return "account/accountForm";
    }

}
