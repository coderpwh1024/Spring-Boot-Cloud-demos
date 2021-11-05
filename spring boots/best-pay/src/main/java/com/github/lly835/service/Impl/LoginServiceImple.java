package com.github.lly835.service.Impl;

import com.alibaba.fastjson.JSON;
import com.github.lly835.entity.dto.resp.EncryptResponseDTO;
import com.github.lly835.service.LoginService;
import com.github.lly835.util.DESUtil;
import com.github.lly835.util.DateUtils;
import com.github.lly835.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class LoginServiceImple implements LoginService {


    @Value("${bank.corpNo}")
    private String corpNo;


    @Value("${bank.corpKey}")
    private String corpKey;

    @Value("${bank.SM4}")
    private String SM4;


    @Override
    public EncryptResponseDTO getEncrypt() {


        EncryptResponseDTO response = new EncryptResponseDTO();

        String timestamp = DateUtils.getDate();

        String nonceStr = SignUtil.getUUID32();

        StringBuilder sb = new StringBuilder();

        sb.append(corpNo).append(timestamp).append(nonceStr).append(corpKey);
        log.info("当前签名加密之前为:{}", sb);

        String sing = DigestUtils.md5DigestAsHex(sb.toString().getBytes());
        log.info("当前签名加密之后为:{}", sing);

        response.setNonceStr(nonceStr);
        response.setSing(sing);
        response.setTimestamp(timestamp);


        return response;

    }

    /**
     * 解密
     *
     * @return
     */
    @Override
    public String getDecode(String body) {
        log.info("进入解密方法中,当前的入参为:{}", JSON.toJSONString(body));
        try {
            String result = DESUtil.decrypt(corpKey, body);
            log.info("解密后的结果为:{}", JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            log.info("解密异常:");
            log.info(e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public Object getEncryptTest(String body) {
        log.info("加密测试方法，当前的入参为:{}", JSON.toJSONString(body));
        String result = DESUtil.encrypt(corpKey, body);
        log.info("加密测试方法，加密后的结果为:{}", result);
        return result;
    }


}
