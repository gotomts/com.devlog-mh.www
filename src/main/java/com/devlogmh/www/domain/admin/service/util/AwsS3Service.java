package com.devlogmh.www.domain.admin.service.util;

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

@Component
public class AwsS3Service {

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Autowired
    private AppSettingS3Util appSettingS3Util;

    public PutObjectResult upload(MultipartFile file) {

        PutObjectResult putObjectResult;

        try (InputStream inputStream = file.getInputStream()) {

            // ファイル名を取得
            String fileName = file.getOriginalFilename();

            // ファイルのMetadataを取得
            final ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

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

}
