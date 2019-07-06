package com.devlogmh.www.domain.admin.util;

import com.devlogmh.www.domain.admin.service.aws.AwsS3Service;
import com.devlogmh.www.domain.model.post.PostDto;
import com.devlogmh.www.domain.model.post.PostForm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.devlogmh.www.domain.admin.util.Contains.UPLOAD_FILE_NAME;

/**
 * 画像アップロード 共通クラス
 */
public class FileUploadUtil {

    /**
     * 画像アップロード処理
     * @param postDto
     * @param inputForm
     */
    public static void topImageUpload(AwsS3Service awsS3Service, PostDto postDto, PostForm inputForm) {

        // 画像名を自動生成
        String fileName = FileUploadUtil.getName(inputForm.getUploadFile().getOriginalFilename());

        // 画像のアップロード
        awsS3Service.upload(inputForm.getUploadFile(), fileName);

        // アイキャッチ画像URLをDTOに詰める
        postDto.setTopImageUrl(awsS3Service.getUrl(fileName));

        // 画像のtitle、altタグ情報をDTOに詰める
        postDto.setTopImageTitle(inputForm.getTitle());
        postDto.setTopImageAlt(inputForm.getTitle());

    }

    /**
     * ファイル名を作成して取得
     */
    private static String getName(String filename) {

        // 変数を初期化
        String name = null;

        // Calendarクラスのオブジェクトを生成
        Calendar calendar = Calendar.getInstance();

        // 日付のフォーマットパターンを設定
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = simpleDateFormat.format(calendar.getTime());

        // 正規表現で拡張子を取得
        String regex = "(.jpg|.jpeg|.png|.gif|.pdf)";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(filename);

        if (matcher.find()) {
            String matchrStr = matcher.group();
            name = UPLOAD_FILE_NAME + time + matchrStr;
        }

        return name;

    }

}
