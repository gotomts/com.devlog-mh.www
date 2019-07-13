package com.devlogmh.www.domain.admin.util;

import javax.servlet.http.HttpServletRequest;

import static com.devlogmh.www.domain.contains.WebInfoContains.DEV_PORT;

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

        // 開発環境の場合
        if (serverPort == DEV_PORT) {
            rootpath = schema + "://" + serverName + ":" + serverPort + contextPath + "/";

        // 本番環境の場合
        } else {
            rootpath = schema + "://" + serverName + contextPath + "/";
        }

        return  rootpath;

    }

}
