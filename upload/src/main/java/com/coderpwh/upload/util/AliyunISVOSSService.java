package com.coderpwh.upload.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;

/**
 *  ISV的Service
 *
 * @description:
 * @author: pengwenhao
 * @date: 2020/5/8 0008 17:26
 */
@Component
public class AliyunISVOSSService {


    @Autowired
    private OssIsvConfigureProperties properties;

    private Object lock = new Object();

    // 创建OSSClient实例
    private OSSClient client;

    @PostConstruct
    public void initClient() {
        synchronized (lock) {
            if (client == null) {
                client = new OSSClient(properties.getEndPoint(), properties.getAccessKeyId(),properties.getAccessKeySecret());
            }
        }
    }

    public String getAccessUrlPrefix() {
        return properties.getAccessUrlPrefix();
    }


    public void uploadToOSS(InputStream fileInputStream, String fileName) throws Exception {
        try {
            if (fileName.startsWith("/")) {
                fileName = fileName.substring(1);
            }
            // 上传文件流
            client.putObject(properties.getBucketName(), fileName, fileInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    /**
     *
     * @param files
     * @param bucketName
     * @author yangdx
     * @return
     */
    public Map<String, List<Map<String, String>>> uploadToOSS(List<MultipartFile> files, String bucketName) {
        try {
            if (files != null && files.size() > 0) {
                if (!StringUtils.isEmpty(bucketName) && bucketName.startsWith("/")) {
                    bucketName = bucketName.substring(1);
                }

                Map<String, List<Map<String, String>>> results = new HashMap<String, List<Map<String, String>>>();
                Map<String, String> fileItemMap = null;
                List<Map<String, String>> inputFileItems = null;
                for (MultipartFile file : files) {
                    String inputName = file.getName(); // inputName名
                    String originalFilename = file.getOriginalFilename();// 原文件名
                    String randomFileName = String.valueOf(System.nanoTime())
                            + originalFilename.substring(originalFilename.lastIndexOf("."));

                    String ossFullPath = StringUtils.isEmpty(bucketName) ? randomFileName
                            : bucketName + (bucketName.endsWith("/") ? "" : "/") + randomFileName;
                    uploadToOSS(file.getInputStream(), ossFullPath);

                    inputFileItems = results.get(inputName);
                    if (inputFileItems == null) {
                        inputFileItems = new ArrayList<Map<String, String>>();
                    }

                    fileItemMap = new HashMap<String, String>();
                    fileItemMap.put("ossFullPath", ossFullPath);
                    fileItemMap.put("originalFilename", originalFilename);
                    inputFileItems.add(fileItemMap);

                    results.put(inputName, inputFileItems);
                }

                return results;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param files
     * @param bucketName
     * @author yangdx
     * @return
     */
    public Map<String, List<Map<String, String>>> uploadToOSS(MultipartFile[] files, String bucketName) {
        if (files != null && files.length > 0) {
            return uploadToOSS(Arrays.asList(files), bucketName);
        }
        return null;
    }

    /**
     * 根据key获取OSS服务器上的文件输入流
     *
     * @param fileUrl
     * @param fileUrl
     * @return
     */
    public final InputStream getInputStream(String fileUrl) {
        OSSObject oob = client.getObject(properties.getBucketName(), fileUrl);
        if (oob != null) {
            return oob.getObjectContent();
        }
        return null;
    }



}
