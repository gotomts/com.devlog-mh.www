package com.devlogmh.www.app.admin.user;

import com.devlogmh.www.domain.admin.service.users.UsersFormService;
import com.devlogmh.www.domain.admin.service.users.UsersService;
import com.devlogmh.www.domain.model.users.UsersDto;
import com.devlogmh.www.domain.model.users.UsersForm;
import com.devlogmh.www.domain.model.users.UsersListForm;
import com.devlogmh.www.exception.DuplicateProductException;
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

    @Autowired
    private UsersFormService usersFormService;

    /*------------ Viewのパス設定 ---------------*/

    /**
     * 一覧
     */
    private final String USER_MASTER = "app/admin/user/user-master";

    /**
     * 新規登録画面
     */
    private final String USER_MASTER_NEW = "app/admin/user/user-master-new";

    /**
     * 編集画面
     */
    private final String USER_MASTER_EDIT = "app/admin/user/user-master-edit";

    /**
     * ゴミ箱 一覧
     */
    private final String USER_MASTER_TRASH = "app/admin/user/user-master-trash-list";

    /**
     * リダイレクト
     * ユーザー管理
     */
    private final String USER_MASTER_REDIRECT = "redirect:/admin/user-master/0";

    /**
     * リダイレクト
     * ユーザー管理 ゴミ箱
     */
    private final String USER_MASTER_DELETE_REDIRECT = "redirect:/admin/user-master/delete-list/0";


    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping
    public ModelAndView index(ModelAndView mav) {

        // ビューの設定
        mav.setViewName(USER_MASTER_REDIRECT);

        return mav;

    }

    /**
     * 一覧表示
     * @param mav
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView index(@ModelAttribute("errorMsg") String errorMsg, @PathVariable int id, ModelAndView mav) {

        // ビューの設定
        mav.setViewName(USER_MASTER);

        // エラーがあったら表示
        if (StringUtils.isNotEmpty(errorMsg)) {
            mav.addObject("errorMsg", errorMsg);
        }

        // サービスの初期処理
        PagedListHolder<UsersDto> pagedListHolder = usersService.init(id);
        mav.addObject("pagedListHolder", pagedListHolder);

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
            mav.setViewName(USER_MASTER_REDIRECT);
            return mav;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(inputForm.getDelflg());
        usersDto.setCheckId(inputForm.getCheckId());

        // ゴミ箱へ移動する
        usersService.trashAdd(usersDto);

        // リダイレクト先
        mav = new ModelAndView(USER_MASTER_REDIRECT);
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
            mav.setViewName(USER_MASTER_DELETE_REDIRECT);
            return mav;

        }

        // formからdtoへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(inputForm.getDelflg());
        usersDto.setCheckId(inputForm.getCheckId());

        // ゴミ箱から戻す
        usersService.trashRemove(usersDto);

        // リダイレクト先
        mav = new ModelAndView(USER_MASTER_REDIRECT);
        return mav;
    }


    /**
     * ゴミ箱を見る リダイレクト
     */
    @GetMapping("delete-list")
    public ModelAndView deleteList(ModelAndView mav) {
        // リダイレクト先
        mav = new ModelAndView(USER_MASTER_DELETE_REDIRECT);
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
            mav.setViewName(USER_MASTER_DELETE_REDIRECT);
            return mav;

        }

        // formオブジェクトからDTOへ詰め替え
        UsersDto usersDto = new UsersDto();
        usersDto.setDelflg(inputForm.getDelflg());
        usersDto.setCheckId(inputForm.getCheckId());

        // 削除処理
        usersService.destroy(usersDto);

        mav = new ModelAndView(USER_MASTER_DELETE_REDIRECT);
        return mav;
    }


    /**
     * 新規作成画面 表示
     * @param mav
     * @return
     */
    @GetMapping("new")
    public ModelAndView newUsers(UsersForm inputForm, ModelAndView mav) {
        mav.setViewName(USER_MASTER_NEW);
        // フォームの初期設定
        usersFormService.setupForm(inputForm);
        // オブジェクトを詰め込み
        mav.addObject("form", inputForm);
        return mav;
    }

    /**
     * 新規作成 登録処理
     * @param inputForm
     * @return
     */
    @PostMapping("new")
    public ModelAndView create(@ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav) throws DuplicateProductException {

        // フォームの初期設定
        usersFormService.setupForm(inputForm);

        // 独自バリデーションチェック実装
        usersFormService.validate(inputForm, result);

        // エラーだった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_NEW);
            // オブジェクトを詰め込み
            mav.addObject("form", inputForm);
            return mav;
        }

        // 保存処理
        usersFormService.save(inputForm);
        // リダイレクト設定
        mav = new ModelAndView(USER_MASTER_REDIRECT);
        return mav;
    }

    /**
     * 編集画面 表示
     */
    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, UsersForm inputForm, ModelAndView mav) {
        // ビューの設定
        mav.setViewName(USER_MASTER_EDIT);
        // フォームの初期設定
        usersFormService.setupForm(id, inputForm);
        // オブジェクトを詰め込み
        mav.addObject("form", inputForm);
        return mav;
    }

    /**
     * 編集画面 更新処理
     */
    @PutMapping("edit/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute("form") @Validated UsersForm inputForm, BindingResult result, ModelAndView mav) throws DuplicateProductException {

        // フォームの初期設定
        usersFormService.setupForm(inputForm);

        // エンティティ生成
        UsersDto usersDto = new UsersDto();

        usersFormService.validate(id, inputForm, result);

        // エラーだった場合
        if (result.hasErrors()) {
            // ビューの設定
            mav.setViewName(USER_MASTER_EDIT);
            // オブジェクトを詰め込み
            mav.addObject("form", inputForm);
            return mav;
        }

        // 更新する対象のIDを設定
        usersDto.setId(id);

        // 更新処理
        usersFormService.update(usersDto, inputForm);

        // 更新後のリダイレクト先
        mav = new ModelAndView(USER_MASTER_REDIRECT);
        return mav;
    }


}
