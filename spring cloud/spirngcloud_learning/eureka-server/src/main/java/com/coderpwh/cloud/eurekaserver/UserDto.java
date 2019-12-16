package com.coderpwh.cloud.eurekaserver;

import java.io.Serializable;

/**
 * @author coderpwh
 * @create 2019-12-16 21:50
 * @desc ${DESCRIPTION}
 **/


public class UserDto implements Serializable{

    private  Long viewTimeStamp;


    private  Long userId;

    private  String userNam;

    public Long getViewTimeStamp() {
        return viewTimeStamp;
    }

    public void setViewTimeStamp(Long viewTimeStamp) {
        this.viewTimeStamp = viewTimeStamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNam() {
        return userNam;
    }

    public void setUserNam(String userNam) {
        this.userNam = userNam;
    }
}
