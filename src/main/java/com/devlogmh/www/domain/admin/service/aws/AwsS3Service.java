package com.devlogmh.www.domain.admin.service.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.devlogmh.www.domain.admin.util.AppSettingS3Util;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * AWS S3利用サービス
 */
@Component
public class AwsS3Service {

    /**
     * AmazonS3クライアント情報
     */
    @Autowired
    private AmazonS3Client amazonS3Client;

    /**
     * S3利用にあたって必要な情報
     */
    @Autowired
    private AppSettingS3Util appSettingS3Util;

    /**
     * ファイルアップロード
     * @param file
     * @return
     */
    public PutObjectResult upload(MultipartFile file, String fileName) {

        PutObjectResult putObjectResult;

        try (InputStream inputStream = file.getInputStream()) {

            // ファイルのMetadataを取得
            final ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            // PutObjectにアップロード時の情報を詰める
            PutObjectRequest putObjectRequest = new PutObjectRequest(appSettingS3Util.getBucketUploadPath(), fileName, inputStream, metadata);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            putObjectResult = amazonS3Client.putObject(putObjectRequest);
            // close処理
            IOUtils.closeQuietly(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("S3へのファイル保存に失敗しました。");
        }

        return putObjectResult;
    }

    /**
     * URLを取得
     * @param fileName
     * @return url
     */
    public String getUrl(String fileName) {

        // 変数の初期化
        String url = null;

        // URLを取得
        url = amazonS3Client.getUrl(appSettingS3Util.getBucketUploadPath(), fileName).toString();

        return url;

    }

}
