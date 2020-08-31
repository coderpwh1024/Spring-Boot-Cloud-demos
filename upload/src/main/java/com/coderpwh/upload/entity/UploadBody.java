package com.coderpwh.upload.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/5/8 0008 16:00
 */
@ApiModel(value = "文件上传入参")
public class UploadBody implements Serializable {

    private static final long serialVersionUID = 5549893924341143881L;

    @ApiModelProperty(value = "字节流数组", example = "字节", position = 1, required = true)
    private byte[] file;

    @ApiModelProperty(value = "可选字段，文件名称，如果传了代表上传到指定路径，优先级最高", example = "", position = 2)
    private String fileName;

    @ApiModelProperty(value = "分区路径名称，文件名称，如果传了代表随机生成文件名称", example = "", position = 3)
    private String bucketName;

    @ApiModelProperty(value = "可选字段，返回路径类型，和bucketName组合使用，如果为1代表返回图片完成路径，其他代表返回文件名称", example = "", position = 4)
    private Integer returnPathType;

    @ApiModelProperty(value = "可选字段，文件类型，和bucketName组合使用，例如jp", example = "", position = 5)
    private String fileType;

    @ApiModelProperty(value = "可选（isv）不传就是默认 rent(全部是小写)", example = "isv", position = 6)
    private String buinessId;

    public String getBuinessId() {
        return buinessId;
    }

    public void setBuinessId(String buinessId) {
        this.buinessId = buinessId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UploadBody() {
    }

    public String getFileName() {
        return this.fileName;
    }

    public byte[] getFile() {
        return this.file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Integer getReturnPathType() {
        return this.returnPathType;
    }

    public void setReturnPathType(Integer returnPathType) {
        this.returnPathType = returnPathType;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
