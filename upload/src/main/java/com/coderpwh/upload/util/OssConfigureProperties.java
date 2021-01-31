package com.coderpwh.upload.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangdx
 */
@Configuration
@ConfigurationProperties(prefix="aliyun.oss")
public class OssConfigureProperties {
	
    private String bucketName;

    private String endPoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String accessUrlPrefix;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getAccessUrlPrefix() {
		return accessUrlPrefix;
	}

	public void setAccessUrlPrefix(String accessUrlPrefix) {
		this.accessUrlPrefix = accessUrlPrefix;
	}

}
