package com.devlogmh.www.domain.admin.util;

import javax.servlet.http.HttpServletRequest;

/**
 * サイトの情報に関するクラス
 */
public class SiteInfoUtil {

    public static String getRootPath(HttpServletRequest request) {

        // 変数を初期化
        String rootpath = null;

        // URLを生成
        String schema = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        rootpath = schema + "://" + serverName + ":" + serverPort + contextPath + "/";

        return  rootpath;

    }

}
