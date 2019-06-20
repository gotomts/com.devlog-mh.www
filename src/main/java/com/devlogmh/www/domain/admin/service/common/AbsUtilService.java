package com.devlogmh.www.domain.admin.service.common;

/**
 * 基底クラス
 */
public abstract class AbsUtilService implements BaseService {

    /**
     * 必ず実行したい処理を記述
     * @param dto
     */
    public void delegate(Object dto) {

        this.customInit();
        this.mainProcess();

    }

    public abstract void customInit();

    /**
     * 主処理
     * @return
     */
    public abstract void mainProcess();

}
