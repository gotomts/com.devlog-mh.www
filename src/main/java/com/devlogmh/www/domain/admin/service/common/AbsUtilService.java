package com.devlogmh.www.domain.admin.service.common;

import com.devlogmh.www.domain.model.session.SessionData;
import com.devlogmh.www.domain.model.util.BaseControlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基底クラス
 */
public abstract class AbsUtilService implements BaseService {

    /**
     * ログインセッション情報
     */
    @Autowired
    private SessionData sessionData;

    /**
     * ModelAndView
     */
    private ModelAndView mav;

    /**
     * 必ず実行したい処理を記述
     * @param dto
     */
    public void delegate(BaseControlDto dto) {

        // ログインセッションが存在するか確認
//        if (Objects.nonNull(sessionData)) {
            // ログイン情報をmavに格納
            dto.getMav().addObject("isLogin", this.sessionData.isLogin());
//        }

        // 初期化処理
        this.customInit();

        // 主処理
        this.mainProcess();

    }

    /**
     * 初期化処理
     */
    public abstract void customInit();

    /**
     * 主処理
     * @return
     */
    public abstract void mainProcess();

}
