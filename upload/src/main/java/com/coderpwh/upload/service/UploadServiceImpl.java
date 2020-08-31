package com.coderpwh.upload.service;


import com.coderpwh.upload.constant.UploadTypeEnum;
import com.coderpwh.upload.controller.UploadAliController;
import com.coderpwh.upload.entity.ResponseStatus;
import com.coderpwh.upload.entity.UploadBody;
import com.coderpwh.upload.resp.ResponseResult;
import com.coderpwh.upload.util.AliyunISVOSSService;
import com.coderpwh.upload.util.AliyunOSSService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/5/8 0008 17:48
 */
@Component
public class UploadServiceImpl {


    private static final Logger logger = LoggerFactory.getLogger(UploadAliController.class);

    @Resource
    private AliyunOSSService aliyunOSSService;

    @Resource
    private AliyunISVOSSService isvossService;


    /***
     *  获取上传路径前缀
     * @param buinessId
     * @return
     */
    public String getAccessUrlPrefix(String  buinessId) {
        if (StringUtils.isNotBlank(buinessId) && buinessId.equals(UploadTypeEnum.ISV.getType())) {
            return isvossService.getAccessUrlPrefix();
        } else {
            return aliyunOSSService.getAccessUrlPrefix();
        }
    }




    /***
     *  上传单个文件
     * @param uploadBody
     * @return
     */
    public ResponseResult<String> uploadToOSS(UploadBody uploadBody) {
        try {

            InputStream sbs = new ByteArrayInputStream(uploadBody.getFile());

            //  ISV 服务
            if (StringUtils.isNotBlank(uploadBody.getBuinessId())&& uploadBody.getBuinessId().equals(UploadTypeEnum.ISV.getType())) {
                if (uploadBody.getFileName() != null) {
                    isvossService.uploadToOSS(sbs, uploadBody.getFileName());
                    return ResponseResult.buildSuccessResponse(isvossService.getAccessUrlPrefix() + uploadBody.getFileName());
                }
                if (uploadBody.getBucketName() != null) {
                    String randomFileName = String.valueOf(System.nanoTime()) + "_" + UUID.randomUUID().toString().replaceAll("-", "");

                    String ossFullPath = uploadBody.getBucketName() + "/" + randomFileName + uploadBody.getFileType();

                    isvossService.uploadToOSS(sbs, ossFullPath);
                    if (uploadBody.getReturnPathType() == 1) {
                        return ResponseResult.buildSuccessResponse(isvossService.getAccessUrlPrefix() + ossFullPath);
                    } else {
                        return ResponseResult.buildSuccessResponse(ossFullPath);
                    }

                }
            } else {
                //  爱租机服务
                if (uploadBody.getFileName() != null) {
                    aliyunOSSService.uploadToOSS(sbs, uploadBody.getFileName());
                    return ResponseResult.buildSuccessResponse(aliyunOSSService.getAccessUrlPrefix() + uploadBody.getFileName());
                }
                if (uploadBody.getBucketName() != null) {
                    String randomFileName = String.valueOf(System.nanoTime()) + "_" + UUID.randomUUID().toString().replaceAll("-", "");

                    String ossFullPath = uploadBody.getBucketName() + "/" + randomFileName + uploadBody.getFileType();

                    aliyunOSSService.uploadToOSS(sbs, ossFullPath);
                    if (uploadBody.getReturnPathType() == 1) {
                        return ResponseResult.buildSuccessResponse(aliyunOSSService.getAccessUrlPrefix() + ossFullPath);
                    } else {
                        return ResponseResult.buildSuccessResponse(ossFullPath);
                    }

                }
            }

            return ResponseResult.build(ResponseStatus.FILE_UPLOAD_FAILED.getCode(),
                    ResponseStatus.FILE_UPLOAD_FAILED.getMessage(),
                    "上传图片失败");
        } catch (Exception e) {
            logger.error("uploadImg失败：{}", e);
            return ResponseResult.build(ResponseStatus.FILE_UPLOAD_FAILED.getCode(),
                    ResponseStatus.FILE_UPLOAD_FAILED.getMessage(),
                    e.getMessage());
        }
    }


}
