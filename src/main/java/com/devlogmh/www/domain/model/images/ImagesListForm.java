package com.devlogmh.www.domain.model.images;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 画像管理一覧フォームクラス
 */
public class ImagesListForm implements Serializable {

    /**
     *  画像ID
     */
    @Getter
    @Setter
    private Long id;

    /**
     * 画像ファイル
     */
    @Getter
    @Setter
    private MultipartFile uploadFile;

    /**
     * URL
     */
    @Getter
    @Setter
    private String url;

    /**
     * title
     */
    @Getter
    @Setter
    private String title;

    /**
     * alt
     */
    @Getter
    @Setter
    private String alt;

}
