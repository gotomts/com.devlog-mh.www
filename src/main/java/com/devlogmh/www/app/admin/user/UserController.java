package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.admin.service.users.UsersService;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersListForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ユーザ管理コントローラー
 */
@Controller
@RequestMapping("/admin/user-master")
public class UserController {

    /*------------ DI ---------------*/

    @Autowired
    private UsersService usersService;

    /*------------ Viewのパス設定 ---------------*/

    /**
     * 一覧
     */
    private final String USER_MASTER = "app/admin/user/user-master";

    /**
     * ゴミ箱 一覧
     */
    private final String USER_MASTER_TRASH = "app/admin/user/user-master-trash-list";

    /**
     * リダイレクト
     * ユーザー管理
     */
    private final String REDIRECT_USER_MASTER = "redirect:/admin/user-master/0";

    /**
     * リダイレクト
     * ユーザー管理 ゴミ箱
     */
    private final String REDIRECT_USER_MASTER_DELETE = "redirect:/admin/user-master/delete-list/0";


    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav) {

        // ビューの設定
        mav.setViewName(REDIRECT_USER_MASTER);

        return mav;

    }

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg, @PathVariable int id, ModelAndView mav) {

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            mav.addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<UsersDto> pagedListHolder = usersService.init(id);
        mav.addObject("pagedListHolder", pagedListHolder);

        // ビューの設定
        mav.setViewName(USER_MASTER);
        return mav;

    }

    /**
     * ゴミ箱へ移動
     */
    @PostMapping("trash-add")
    public ModelAndView trashAdd(@ModelAttribute("pagedListHolder") @Validated UsersListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // エラーだった場合
        if (result.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "ゴミ箱へ移動するユーザーを選択してください。";
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            // ビューの設定
            mav.setViewName(REDIRECT_USER_MASTER);
            return mav;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(inputForm.getDelflg());
        usersDto.setCheckId(inputForm.getCheckId());

        // ゴミ箱へ移動する
        usersService.trashAdd(usersDto);

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;

    }

    /**
     * ゴミ箱から戻す
     */
    @PostMapping("trash-remove")
    public ModelAndView trashRemove(@ModelAttribute("pagedListHolder") @Validated UsersListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // エラーだった場合
        if (result.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "ゴミ箱から戻すユーザーを選択してください。";
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            // ビューの設定
            mav.setViewName(REDIRECT_USER_MASTER_DELETE);
            return mav;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(inputForm.getDelflg());
        usersDto.setCheckId(inputForm.getCheckId());

        // ゴミ箱から戻す
        usersService.trashRemove(usersDto);

        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER);
        return mav;
    }


    /**
     * ゴミ箱を見る リダイレクト
     */
    @GetMapping("delete-list")
    public ModelAndView deleteList(ModelAndView mav) {
        // リダイレクト先
        mav = new ModelAndView(REDIRECT_USER_MASTER_DELETE);
        return mav;
    }

    /**
     * ゴミ箱 一覧
     */
    @GetMapping("delete-list/{id}")
    public ModelAndView deleteList(@PathVariable int id, ModelAndView mav) {

        // サービスの初期処理
        PagedListHolder<UsersDto> pagedListHolder = usersService.delList(id);
        mav.addObject("pagedListHolder", pagedListHolder);

        // ビューの設定
        mav.setViewName(USER_MASTER_TRASH);
        return mav;

    }

    /**
     * ゴミ箱のチェックしたデータを削除
     */
    @PostMapping("destroy")
    public ModelAndView destroy(@ModelAttribute("pagedListHolder") @Validated UsersListForm inputForm, BindingResult result, ModelAndView mav, RedirectAttributes redirectAttributes) {

        // エラーだった場合
        if (result.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "削除するユーザーを選択してください。";
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            // ビューの設定
            mav.setViewName(REDIRECT_USER_MASTER_DELETE);
            return mav;

        }

        // formオブジェクトからDTOへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(inputForm.getDelflg());
        usersDto.setCheckId(inputForm.getCheckId());

        // 削除処理
        usersService.destroy(usersDto);

        mav = new ModelAndView(REDIRECT_USER_MASTER_DELETE);
        return mav;
    }

}
