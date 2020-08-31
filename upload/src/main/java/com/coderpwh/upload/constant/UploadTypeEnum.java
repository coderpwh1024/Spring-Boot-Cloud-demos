package com.coderpwh.upload.constant;


/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/5/8 0008 17:41
 */

public enum UploadTypeEnum {

    WOAIZUJI("rent", "rent"),
    ISV("isv", "isv");


    private String type;

    private String message;

    UploadTypeEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
