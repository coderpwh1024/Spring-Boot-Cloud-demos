package com.github.lly835.controller;

import com.github.lly835.entity.WxUser;
import com.github.lly835.entity.dto.resp.EncryptResponseDTO;
import com.github.lly835.resp.ResponseResult;
import com.github.lly835.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/login/")
@Api(value = "登录接口", tags = "登录接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/encrypt", method = RequestMethod.GET)
    @ApiOperation(value = "加密", notes = "加密")
    public ResponseResult<EncryptResponseDTO> getEncrypt() {
        EncryptResponseDTO response = loginService.getEncrypt();
        return ResponseResult.buildSuccessResponse(response);
    }

    @RequestMapping(value = "/encrypt/test", method = RequestMethod.GET)
    @ApiOperation(value = "加密测试", notes = "加密测试")
    public ResponseResult<?> getEncryptTest(String body) {
        Object obj = loginService.getEncryptTest(body);
        return ResponseResult.buildSuccessResponse(obj);
    }


    @RequestMapping(value = "/decode", method = RequestMethod.GET)
    @ApiOperation(value = "解密", notes = "解密")
    public ResponseResult<?> getDecode(@RequestParam(required = true) String body) {
        String sign = loginService.getDecode(body);
        return ResponseResult.buildSuccessResponse(sign);
    }

}
