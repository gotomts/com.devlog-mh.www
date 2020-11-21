package com.devlogmh.www.domain.model.session;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 画像管理 アップロード時 セッション
 */
@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionImagesData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * title
     * 初期値にはファイル名を挿入します。
     */
    private String title;

    /**
     * url
     */
    private String url;

}
