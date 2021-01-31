package com.coderpwh.upload.controller;


import com.coderpwh.upload.entity.UploadBody;
import com.coderpwh.upload.entity.dto.UploadType;
import com.coderpwh.upload.resp.ResponseResult;
import com.coderpwh.upload.service.UploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/uploadAli")
@Api(value = "文件上传")
@Slf4j
public class UploadAliController {


    @Resource
    private UploadServiceImpl uploadService;


    /**
     * 获取上传路径前缀
     *
     * @return 上传路径前缀
     */
    @RequestMapping(value = "/getAccessUrlPrefix", method = RequestMethod.POST)
    @ApiOperation(value = "获取上传路径前缀", notes = "获取上传路径前缀")
    public String getAccessUrlPrefix(@RequestBody UploadType uploadType) {
        return uploadService.getAccessUrlPrefix(uploadType.getBusinessid());
    }


    /****
     *   上传文件
     * @param uploadBody
     * @return
     */
    @RequestMapping(value = "/uploadToOSSFileInputStrem", method = RequestMethod.POST)
    @ApiOperation(value = "上传文件", notes = "上传文件")
    public ResponseResult<String> uploadToOSSFileInputStrem(@RequestBody UploadBody uploadBody) {
        return uploadService.uploadToOSS(uploadBody);
    }


    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ApiOperation(value = "直接上传文件", notes = "直接上传文件")
    public ResponseResult<String> uploadImage(@RequestParam("attaFile") MultipartFile file) {
        try {
            UploadBody dataMap = new UploadBody();
            String originalFilename = file.getOriginalFilename();// 原文件名
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));

            dataMap.setFile(IOUtils.toByteArray(file.getInputStream()));
            dataMap.setBucketName("rec/manage/");
            dataMap.setReturnPathType(1);
            dataMap.setFileType(fileType);
            ResponseResult<String> d =  uploadService.uploadToOSS(dataMap);
            if(d!=null&&d.isSuccess()){ //上传失败直接提示
                //返回json
                return ResponseResult.buildSuccessResponse(d.getData());
            }
            return ResponseResult.build(d.getErrCode(), d.getErrMsg(), null);

        } catch (Exception e) {
            log.error("上传文件失败：{}",e.getLocalizedMessage());
            return ResponseResult.build(1000, "附件上传失败", null);
        }
    }


    @PostMapping(value = "/test")
    public ResponseResult<String> test() throws Exception {

        byte[] imageByte;
        File file1 = new File("C:\\Users\\Administrator\\Desktop\\2019-12-01_海边倒影.jpg");//需要转换成二进制字节流的文件的绝对路径
        FileInputStream fls = new FileInputStream(file1);
        imageByte = new byte[(int) file1.length()];
        fls.read(imageByte);
        fls.close();


        final String OSS_BUCKET_MER_QR = "mer/qr/";

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        UploadBody uploadBody = new UploadBody();
        uploadBody.setBucketName(OSS_BUCKET_MER_QR);
        uploadBody.setFile(imageByte);
        uploadBody.setFileType(".png");
        uploadBody.setReturnPathType(1);
        uploadBody.setBuinessId("ISV");

        return uploadService.uploadToOSS(uploadBody);


    }


}
