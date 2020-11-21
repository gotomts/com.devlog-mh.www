package com.devlogmh.www.domain.admin.util;

import javax.servlet.http.HttpServletRequest;

import static com.devlogmh.www.domain.contains.WebInfoContains.DEV_PORT;

/**
 * サイトの情報に関するクラス
 */
public class SiteInfoUtil {

    /**
     * 現在のRequestURLからパスパラメータを削除したURLを取得
     * @param request
     * @param pathNum
     * @return
     */
    public static String getCurrentURL(HttpServletRequest request, String pathNum) {

        String currentPath = null;

        // requestURLを取得
        String requestURL = new String(request.getRequestURL());

        // パスパラメータが存在したら一旦削除
        String regex1 = "/" + pathNum;
        currentPath = requestURL.replaceAll(regex1, "");

        // 末尾に/(スラッシュ)が付いてなかったら付与する
        String regex2 = ".*/$";
        if (!requestURL.matches(regex2)) {
            currentPath = currentPath + "/";
        }

        return currentPath;

    }

    /**
     * ルートパスを取得
     * @param request
     * @return
     */
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
