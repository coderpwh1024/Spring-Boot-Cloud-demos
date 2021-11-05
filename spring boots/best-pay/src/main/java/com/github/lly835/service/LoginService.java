package com.github.lly835.service;

import com.github.lly835.entity.dto.resp.EncryptResponseDTO;

public interface LoginService {

    /***
     * 加密
     * @return
     */
    EncryptResponseDTO getEncrypt();

    /***
     * 解密
     * @return
     */
    String getDecode(String body);


    /***
     * 加密测试
     * @return
     */
    Object getEncryptTest(String body);

}
