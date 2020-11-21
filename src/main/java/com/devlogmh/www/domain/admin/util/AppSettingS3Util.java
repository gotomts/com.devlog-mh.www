package com.devlogmh.www.domain.admin.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.ymlにS3として設定した値を取得します
 */
@Component
@ConfigurationProperties(prefix = "s3")
public class AppSettingS3Util {

    @Getter
    @Setter
    private String bucketName;

    @Getter
    @Setter
    private String uploadPath;

    public String getBucketUploadPath() {
        return bucketName + uploadPath;
    }
}
